package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.CustomerValidateRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.CustomerInfoResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.PageResponse;
import com.oxlxs.hotelmanagementsysback.entity.Customer;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public Long validate(CustomerValidateRequest request) throws RuntimeException {
        Customer customer = customerDAO.findByIdNumber(request.getIdNumber())
                .orElse(null);
        if (customer == null || !Objects.equals(customer.getPhone(), request.getPhone())) {
            customer = new Customer(request);
            return customerDAO.save(customer).getId();
        }else if(!Objects.equals(customer.getName(), request.getName())) {
            throw new CustomerAlreadyExistsException();
        }
        return customer.getId();
    }


    public Long getRoom(String idNumber) throws RuntimeException {
        Customer customer = customerDAO.findByIdNumber(idNumber)
                .orElseThrow(() -> new CustomerNotFoundException());
        
    }


}
