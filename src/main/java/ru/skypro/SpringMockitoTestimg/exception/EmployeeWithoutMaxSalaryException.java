package ru.skypro.SpringMockitoTestimg.exception;

public class EmployeeWithoutMaxSalaryException extends RuntimeException {
    public EmployeeWithoutMaxSalaryException (String message) {
        super(message);
    }
}
