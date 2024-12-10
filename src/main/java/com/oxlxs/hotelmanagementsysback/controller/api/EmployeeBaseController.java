package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.request.*;
import com.oxlxs.hotelmanagementsysback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


// 酒店职员基本业务控制器
@RestController
@RequestMapping("/base/employee")
public class EmployeeBaseController {


    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
