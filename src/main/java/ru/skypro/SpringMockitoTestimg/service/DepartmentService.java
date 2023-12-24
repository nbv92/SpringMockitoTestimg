package ru.skypro.SpringMockitoTestimg.service;

import ru.skypro.SpringMockitoTestimg.model.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    List<Employee> getEmployeeAllByDepartment(Integer departmentId);
    Map<Integer, List<Employee>>  getAllEmployees();
}
