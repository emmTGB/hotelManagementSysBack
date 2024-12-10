package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.RoomCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

// 房间的信息控制器
@RestController
@RequestMapping("/api/room")
public class RoomController {
    @PostMapping("/create")
    public ResponseEntity<String > create(@RequestBody RoomCreateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try {
            return ResponseEntity.ok().body("Create success");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Create failed");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String > delete(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body("Delete success");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Delete failed");
        }
    }
}
