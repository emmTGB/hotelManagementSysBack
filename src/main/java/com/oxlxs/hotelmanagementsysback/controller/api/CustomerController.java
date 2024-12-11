package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/room")
    public ResponseEntity<Long> getRoom(@RequestParam String idNumber) {
        try {
            return ResponseEntity.ok().body(customerService.getRoom(idNumber));
        } catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
