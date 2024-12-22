package com.oxlxs.hotelmanagementsysback.service;


import com.oxlxs.hotelmanagementsysback.dto.response.BillResponse;
import com.oxlxs.hotelmanagementsysback.entity.BillRecord;
import com.oxlxs.hotelmanagementsysback.entity.Customer;
import com.oxlxs.hotelmanagementsysback.entity.Room;
import com.oxlxs.hotelmanagementsysback.entity.StayRecord;
import com.oxlxs.hotelmanagementsysback.repository.BillRecordDAO;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillRecordService {

    @Autowired
    private BillRecordDAO billRecordDAO;

    @Autowired
    private CustomerDAO customerDAO;

    public List<BillResponse> list() throws RuntimeException{
        List<BillRecord> billRecords = billRecordDAO.findAll();
        return billRecords.stream().map(
                billRecord -> {
                    BillResponse billResponse = new BillResponse();
                    StayRecord stayRecord = billRecord.getRecord();
                    Room room = stayRecord.getRoom();
                    Customer customer = customerDAO.findByStayRecordAndMainRes(stayRecord, true)
                            .orElse(null);
                    if (customer == null) { return null; }
                    billResponse.setName(customer.getName());
                    billResponse.setRoomId(room.getId());
                    billResponse.setTypeName(room.getType().getName());
                    billResponse.setAmount(billRecord.getAmount());
                    billResponse.setInTime(stayRecord.getCheckinTime());
                    billResponse.setOutTime(stayRecord.getCheckoutTime());
                    return billResponse;
                }
        ).toList();
    }
}
