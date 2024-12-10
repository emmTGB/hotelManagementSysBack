package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.CustomerValidateRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.CustomerInfoResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.PageResponse;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody CustomerValidateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try {
            Long id = customerService.validate(request);
            return ResponseEntity.status(HttpStatus.OK).body(id.toString());
        } catch (CustomerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer already exists");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Register failed");
        }
    }

    @GetMapping("/room")
    public ResponseEntity<String> getRoom(@RequestParam String idNumber) {
        try {
            return ResponseEntity.ok().body(" ");
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Get room failed");
        }
    }

}
