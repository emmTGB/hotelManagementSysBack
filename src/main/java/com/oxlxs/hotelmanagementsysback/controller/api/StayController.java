package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.BookRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CheckInRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CheckOutRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.BookRecordResponse;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerBusyException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.service.BookService;
import com.oxlxs.hotelmanagementsysback.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stay")
public class StayController {

    @Autowired
    private StayService stayService;

    @Autowired
    private BookService bookService;

    @PostMapping("/check/out")
    public ResponseEntity<String> checkOut(
            @RequestBody CheckOutRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try {
            stayService.checkOut(request);
            return ResponseEntity.status(HttpStatus.OK).body("Check out success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check out failed");
        }
    }

    @PostMapping("/check/in")
    public ResponseEntity<String> checkIn(
            @RequestBody CheckInRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try {
            stayService.checkIn(request);
            return ResponseEntity.status(HttpStatus.OK).body("Check in success");
        }catch (RoomNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check in failed");
        }
    }

    @PostMapping("/book")
    public ResponseEntity<String> book(@RequestBody BookRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try{
            bookService.book(request);
            return ResponseEntity.status(HttpStatus.OK).body("Book success");
        }catch (CustomerBusyException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book busy");
        }catch (RoomNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book failed");
        }
    }

    @PostMapping("/book/{id}/in")
    public ResponseEntity<String> bookIn(@PathVariable("id") Long id){
        try{
            bookService.bookIn(id);
            return ResponseEntity.status(HttpStatus.OK).body("Book in success");
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book in failed");
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> unBook(@PathVariable Long id) {
        try{
            bookService.unBook(id);
            return ResponseEntity.status(HttpStatus.OK).body("UnBook successful");
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UnBill failed");
        }
    }

    @GetMapping("/book/list")
    public ResponseEntity<List<BookRecordResponse>> listBook(){
        try{
            return ResponseEntity.ok(bookService.findAll());
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
