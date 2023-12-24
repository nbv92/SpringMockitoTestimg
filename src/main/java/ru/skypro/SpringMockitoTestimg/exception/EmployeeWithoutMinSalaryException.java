package ru.skypro.SpringMockitoTestimg.exception;

public class EmployeeWithoutMinSalaryException extends RuntimeException {
    public EmployeeWithoutMinSalaryException (String message) {
        super(message);
    }
}
