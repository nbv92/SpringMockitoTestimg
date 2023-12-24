package ru.skypro.SpringMockitoTestimg.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.SpringMockitoTestimg.exception.EmployeeWithoutMaxSalaryException;
import ru.skypro.SpringMockitoTestimg.exception.EmployeeWithoutMinSalaryException;
import ru.skypro.SpringMockitoTestimg.model.Employee;
import ru.skypro.SpringMockitoTestimg.service.DepartmentService;
import ru.skypro.SpringMockitoTestimg.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public Integer getSalarySum(Integer departmentId) {
        return employeeService.getAll().values().stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeWithoutMaxSalaryException("Нет максимальной зарплаты"));
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeWithoutMinSalaryException("Нет минимальной зарплаты"));
    }

    @Override
    public List<Employee> getEmployees(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
