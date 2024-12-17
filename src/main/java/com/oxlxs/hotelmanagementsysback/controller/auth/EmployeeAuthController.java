package com.oxlxs.hotelmanagementsysback.controller.auth;

import com.oxlxs.hotelmanagementsysback.dto.request.EmployeeRegisterRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.LoginRequest;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeeAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeeNotExistsException;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeePasswordWrongException;
import com.oxlxs.hotelmanagementsysback.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class EmployeeAuthController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody LoginRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages.toString());
        }

        try{
            Map<String, String > headers = employeeService.login(request);
            return ResponseEntity.ok()
                    .header("Authorization", headers.get("Authorization"))
                    .header("id", headers.get("id"))
                    .header("role", headers.get("role"))
                    .body("Login successfully");
        }catch (EmployeePasswordWrongException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Passwords don't match");
        }catch (EmployeeNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String > register(@RequestBody EmployeeRegisterRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages.toString());
        }

        try{
            Map<String, String > headers = employeeService.register(request);
            return ResponseEntity.ok()
                    .header("Authorization", headers.get("Authorization"))
                    .header("id", headers.get("id"))
                    .header("role", headers.get("role"))
                    .body("Register successfully");
        }catch (EmployeeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee Already Exists");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
