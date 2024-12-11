package com.oxlxs.hotelmanagementsysback.controller.api;

import com.oxlxs.hotelmanagementsysback.dto.response.BillResponse;
import com.oxlxs.hotelmanagementsysback.service.BillRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillRecordService billRecordService;

    @GetMapping("/list")
    public ResponseEntity<List<BillResponse>> list() {
        try{
            return ResponseEntity.ok(billRecordService.list());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
