package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stay")
public class StayController {
    @PostMapping("/check/out")
    public ResponseEntity<String> checkOut(
            @RequestBody CheckOutRequest request, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Check out success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check out failed");
        }
    }

    @PostMapping("/check/in")
    public ResponseEntity<String> checkIn(
            @RequestBody CheckInRequest request, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Check in success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check out failed");
        }
    }

    @PostMapping("/book")
    public ResponseEntity<String> book(
            @RequestBody BookRequest request, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Book success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book failed");
        }
    }

    @PostMapping("/book/in")
    public ResponseEntity<String> bookIn(
            @RequestBody BookInRequest request, BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Book in success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book failed");
        }
    }

    @PostMapping("/book/cancel")
    public ResponseEntity<String> bookCancel(
            @RequestBody SettleStayRequest request, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Book cancel success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book cancel failed");
        }
    }
}
