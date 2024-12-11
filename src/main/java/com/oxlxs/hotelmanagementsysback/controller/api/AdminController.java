package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.response.EmployeeResponse;
import com.oxlxs.hotelmanagementsysback.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController{

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeResponse>> list() {
        try{
            return ResponseEntity.ok(employeeService.list());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String > delete(@PathVariable Long id) {
        try{
            employeeService.delete(id);
            return ResponseEntity.ok().body("Delete success");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Delete failed");
        }
    }

    @PostMapping("/reset/{id}")
    public ResponseEntity<String> reset(@PathVariable Long id) {
        try{
            employeeService.reset(id);
            return ResponseEntity.ok().body("Reset success as \"123456\"");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Reset failed");
        }
    }
}
