package ru.skypro.SpringMockitoTestimg.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.SpringMockitoTestimg.exception.EmployeeAlreadyAddedException;
import ru.skypro.SpringMockitoTestimg.exception.EmployeeNotFoundException;
import ru.skypro.SpringMockitoTestimg.model.Employee;

public class EmployeeServiceImplTest {
//одинтест как копипаст,добавление сотрудника который уже есть в фирме,переполнения хранилища
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldEmployeeAdded() {
        //given
        Employee employee = new Employee("Ivan","Ivanov", 50000,1);
        employeeService.add(employee.getFirstName(),employee.getLastName(),employee.getSalary(),employee.getDepartment()
        );

        //when
        Employee actuallyEmployee = new Employee("Ivan","Ivanov", 50000,1);
        //then
        Assertions.assertEquals(employee, actuallyEmployee);//
    }
    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        //given
        Employee employee = new Employee("Ivan","Ivanov", 50000,1);
        employeeService.add(employee.getFirstName(),employee.getLastName(),employee.getSalary(),employee.getDepartment()
        );

        //when

        //then
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(employee.getFirstName(),employee.getLastName(),employee.getSalary(),employee.getDepartment()
            );
        });
    }

    @Test
    public void shouldThrowEmployeeAlreadyRemoveException() {
        //given
        Employee employee = new Employee("Ivan","Ivanov", 50000,1);
        //when
        //then
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove(employee.getFirstName(),employee.getLastName()
            );
        });
    }
    @Test
    public void shouuldNotFoundTest () {
        //given
        Employee employee = new Employee("Ivan","Ivanov", 50000,1);
        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
        );
        //when
        Employee actuallyEmployee = employeeService.find(employee.getFirstName(),employee.getLastName());
        //then

    }
}
