package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.NewRoomTypeRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.RoomCreateRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.AvailableRoomResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomInfoResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomTypeResponse;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomTypeAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomTypeNotFoundException;
import com.oxlxs.hotelmanagementsysback.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 房间的信息控制器
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String > create(@RequestBody RoomCreateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((msg1, msg2) -> msg1 + ";\n" + msg2)
                            .orElse("Invalid request data"));
        }

        try {
            roomService.create(request);
            return ResponseEntity.ok().body("Create success");
        }catch (RoomAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(RoomTypeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Create failed");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String > delete(@PathVariable Long id){
        try{
            roomService.delete(id);
            return ResponseEntity.ok().body("Delete success");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Delete failed");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoomInfoResponse>> getRooms(){
        try{
            return ResponseEntity.ok(roomService.getRooms());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/type/list")
    public ResponseEntity<List<RoomTypeResponse>> getRoomTypes(){
        try{
            return ResponseEntity.ok(roomService.getRoomTypes());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/type/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createRoomType(@RequestBody NewRoomTypeRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages.toString());
        }

        try{
            roomService.createRoomType(request);
            return ResponseEntity.ok("Create Room Type success");
        }catch (RoomTypeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @DeleteMapping("/type/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRoomType(@PathVariable String name){
        try{
            roomService.deleteRoomType(name);
            return ResponseEntity.ok("Delete Room Type success");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableRoomResponse>> getAvailable(){
        try{
            return ResponseEntity.ok(roomService.getAvailable());
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
