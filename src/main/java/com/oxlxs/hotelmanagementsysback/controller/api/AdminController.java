package com.oxlxs.hotelmanagementsysback.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends EmployeeBaseController{
    @GetMapping("/t")
    public String t() {
        return "Hello World";
    }
}
