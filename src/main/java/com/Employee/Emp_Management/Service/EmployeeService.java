package com.Employee.Emp_Management.Service;

import com.Employee.Emp_Management.Model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
     List<Employee> getEmployee();
     void saveEmployee(Employee employee);

     Employee GetEmployeeById(long id);

     void DeleteEmployeeById(long id);

     Page<Employee> FindPaginated(int page_no,int page_size,String sortField , String sortDirection);
}
