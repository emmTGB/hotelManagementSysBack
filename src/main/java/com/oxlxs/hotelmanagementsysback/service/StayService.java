package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.CheckInRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CheckOutRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CustomerRecordRequest;
import com.oxlxs.hotelmanagementsysback.entity.Customer;
import com.oxlxs.hotelmanagementsysback.entity.StayRecord;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerBusyException;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomDAO;
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

    @Autowired
    RoomDAO roomDAO;

    public void checkOut(CheckOutRequest request) throws RuntimeException  {
        LocalDateTime now = LocalDateTime.now();
        if(!roomDAO.existsById(request.getRoomId())){
            throw new RoomNotFoundException();
        }
        stayRecordDAO.checkout(request.getRoomId(), now);
    }

    public void checkIn(CheckInRequest request) throws RuntimeException {
        List<CustomerRecordRequest> customers = request.getCustomers();

        // 找到本次入住主要负责人
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
            Customer mainC = new Customer(mainCustomer);
            mainC.setStatus(null);
            mainC.setMainRes(true);
            Long uId = customerDAO.save(mainC).getId();
            try {
                stayRecordDAO.checkin(
                        now, request.getRoomId(), request.getDeposit(),
                        uId, mainCustomer.getIdNumber()
                );
            } catch (RuntimeException e) {
                customerDAO.deleteById(uId);
                throw new CustomerBusyException();
            }
            StayRecord stayRecord = customerDAO.findById(uId).orElseThrow(CustomerNotFoundException::new).getStayRecord();
            for(CustomerRecordRequest customer : customers){
                Customer eCustomer = new Customer(customer);
                eCustomer.setStayRecord(stayRecord);
                customerDAO.save(eCustomer);
            }
        }else throw new RuntimeException();
    }
}
