package com.oxlxs.hotelmanagementsysback.exception.customer;

public class CustomerBusyException extends RuntimeException {
    public CustomerBusyException() {
        super("Customer is busy");
    }
}
