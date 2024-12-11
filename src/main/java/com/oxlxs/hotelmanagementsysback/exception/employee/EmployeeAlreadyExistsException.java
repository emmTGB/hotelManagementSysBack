package com.oxlxs.hotelmanagementsysback.exception.employee;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException() {
        super("Employee already exists");
    }
}
