package ru.skypro.SpringMockitoTestimg.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.SpringMockitoTestimg.model.Employee;

import javax.swing.*;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final List<Employee> employees = new ArrayList<>() {{
        add(new Employee("Ivan", "Ivanov", 50000, 1));
        add(new Employee("Petr", "Petrov", 50001, 1));
        add(new Employee("Timofey", "Timohin", 49999, 1));
    }};

    @Test
    public void shouldCorrectlyFindEmployeeByDepartmentId() {
        //given
        int departmentId = 1;
        List<Employee> expectedEmployees = new ArrayList<>() {{
            add(employees.get(0));
            add(employees.get(1));
            add(employees.get(2));
        }};
        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);

        //when
        List<Employee> actualEmployees = departmentService.getEmployees(departmentId);

        //then
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void shouldReturnNullWhenThereAreNoEmployeesInDepartment() {
        //given
        int departmentId = 1;
        Map<String, Employee> employees = new HashMap<>();
        int salary = 100;
        employees.put("key", new Employee("first_name", "last_name", salary, departmentId));
        when(employeeService.getAll()).thenReturn(employees);
        //when
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        //then
        Assertions.assertEquals(salary, employee.getSalary());
    }
        @Test
        public void shouldCorrectlyCalculateSum () {
            //given
            int departmentId = 1;
            int expectedSum = 150000;

            Map<String, Employee> employeeMap = new HashMap<>();
            for (Employee employee : employees) {
                employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
            }

            when(employeeService.getAll()).thenReturn(employeeMap);
            //when
            Integer salarySum = departmentService.getSalarySum(departmentId);

            //then
            Assertions.assertEquals(expectedSum, salarySum);
        }

        @Test
        public void shouldCorrectlyFindMinSalary () {
            //given
            int departmentId = 1;
            Employee expectedEmployee = employees.get(2);

            Map<String, Employee> employeeMap = new HashMap<>();
            for (Employee employee : employees) {
                employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
            }

            when(employeeService.getAll()).thenReturn(employeeMap);
            //when
            Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);

            //then
            Assertions.assertEquals(expectedEmployee, employee);
        }

        @Test
        public void shouldCorrectlyFindMaxSalary () {
            //given
            int departmentId = 1;
            Employee expectedEmployee = employees.get(1);

            Map<String, Employee> employeeMap = new HashMap<>();
            for (Employee employee : employees) {
                employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
            }

            when(employeeService.getAll()).thenReturn(employeeMap);
            //when
            Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);

            //then
            Assertions.assertEquals(expectedEmployee, employee);
        }

        @Test
        public void shouldCorrectlyGroupEmployeesByDepartmentId () {
            //given
            Map<String, Employee> employeeMap = new HashMap<>();
            for (Employee employee : employees) {
                employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
            }

            when(employeeService.getAll()).thenReturn(employeeMap);
            Map<Integer, List<Employee>> expectedMap = new HashMap<>() {{
                put(1, List.of(employees.get(0), employees.get(1), employees.get(2)));
            }};

            //when
            Map<Integer, List<Employee>> actualMap = departmentService.getGroupedByDepartmentEmployees();

            //then
            Assertions.assertEquals(expectedMap, actualMap);
        }
    }

