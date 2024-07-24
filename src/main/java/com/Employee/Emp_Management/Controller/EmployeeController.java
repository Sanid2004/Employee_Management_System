package com.Employee.Emp_Management.Controller;

import com.Employee.Emp_Management.Model.Employee;
import com.Employee.Emp_Management.Service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getEmployee(Model model){
        return findPaginated(1,"firstname","asc",model);
    }

    @GetMapping("/ShowNewEmployeeForm")
    public String ShowNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/SaveEmployee")
    public String SaveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/ShowFormUpdate/{id}")
    public String ShowFormUpdate(@PathVariable (value = "id") long id , Model model){
            Employee employee = employeeService.GetEmployeeById(id);

            model.addAttribute("employee",employee);
            return "Update_Employee";
    }

    @GetMapping("/DeleteEmployee/{id}")
    public String DeleteEmployee(@PathVariable (value = "id") long id){
        this.employeeService.DeleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageno}")
    public String findPaginated(@PathVariable (value = "pageno") int page_no ,
              @RequestParam("sortField") String sortField,
              @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 4;

        Page<Employee> page = employeeService.FindPaginated(page_no,pageSize,sortField,sortDir);
        List<Employee> listemployees = page.getContent();

        model.addAttribute("currentpage",page_no);
        model.addAttribute("totalpages",page.getTotalPages());
        model.addAttribute("totalitems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees",listemployees);
        return "home_page";
    }
}
