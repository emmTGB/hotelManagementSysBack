package com.oxlxs.hotelmanagementsysback.exception.customer;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException() { super("Customer already exists"); }
}
