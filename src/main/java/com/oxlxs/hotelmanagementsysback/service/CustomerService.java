package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public Long getRoom(String idNumber) throws RuntimeException{
        if(!customerDAO.existsByIdNumber(idNumber)){
            throw new CustomerNotFoundException();
        }
        return customerDAO.getRoom(idNumber);
    }
}
