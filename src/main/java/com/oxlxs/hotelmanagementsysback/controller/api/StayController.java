package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.CheckInRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CheckOutRequest;
import com.oxlxs.hotelmanagementsysback.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StayService stayService;

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
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check in failed");
        }
    }
}
