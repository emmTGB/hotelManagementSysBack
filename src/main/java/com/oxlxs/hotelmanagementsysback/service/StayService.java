package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.CheckInRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CustomerRecordRequest;
import com.oxlxs.hotelmanagementsysback.entity.Customer;
import com.oxlxs.hotelmanagementsysback.entity.StayRecord;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import com.oxlxs.hotelmanagementsysback.repository.StayRecordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Service
public class StayService {
    @Autowired
    StayRecordDAO stayRecordDAO;

    @Autowired
    CustomerDAO customerDAO;

    public void checkIn(CheckInRequest request) throws RuntimeException {
        List<CustomerRecordRequest> customers = request.getCustomers();
        CustomerRecordRequest mainCustomer = null;
        Iterator<CustomerRecordRequest> iterator = customers.iterator();
        while (iterator.hasNext()) {
            CustomerRecordRequest customer = iterator.next();
            if(customer.getStatus() == 1){
                mainCustomer = customer;
                iterator.remove();
                break;
            }
        }

        LocalDateTime now = LocalDateTime.now();

        if (mainCustomer != null) {
            Long stayId = stayRecordDAO.checkin(
                    now, request.getRoomId(), request.getDeposit(),
                    mainCustomer.getName(), mainCustomer.getIdNumber(), mainCustomer.getPhone()
            );
            StayRecord stayRecord = stayRecordDAO.findById(stayId)
                    .orElseThrow(RuntimeException::new);
            for(CustomerRecordRequest customer : customers){
                Customer eCustomer = new Customer(customer);
                eCustomer.setStayRecord(stayRecord);
                customerDAO.save(eCustomer);
            }
        }else throw new RuntimeException();
    }
}
