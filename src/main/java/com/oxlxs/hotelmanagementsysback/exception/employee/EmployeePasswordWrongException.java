package com.oxlxs.hotelmanagementsysback.exception.employee;

public class EmployeePasswordWrongException extends RuntimeException {
    public EmployeePasswordWrongException() {
        super("Passwords don't match");
    }
}
