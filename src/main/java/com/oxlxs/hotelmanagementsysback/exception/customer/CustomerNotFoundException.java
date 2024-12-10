package com.oxlxs.hotelmanagementsysback.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() { super("Customer Not Found"); }
}
