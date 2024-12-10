package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.BookRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CheckInRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.SettleStayRequest;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerNotFoundException;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeeNotExistsException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.exception.stay.StayRecordNotExistsException;
import com.oxlxs.hotelmanagementsysback.repository.CustomerDAO;
import com.oxlxs.hotelmanagementsysback.repository.EmployeeDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomDAO;
import com.oxlxs.hotelmanagementsysback.repository.StayRecordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StayService {
    @Autowired
    StayRecordDAO stayRecordDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    RoomDAO roomDAO;

    public void settleStay(SettleStayRequest request) throws RuntimeException {
        Long preId = request.getPreId();
        Long operatorId = request.getOperatorId();

        if(!stayRecordDAO.existsById(preId)){
            throw new StayRecordNotExistsException();
        }

        if(!employeeDAO.existsById(operatorId)){
            throw new EmployeeNotExistsException();
        }

        stayRecordDAO.settleStay(preId, operatorId);
    }

    public void checkIn(CheckInRequest request) throws RuntimeException {
        Long customerId = request.getCustomerId();
        Long roomId = request.getRoomId();
        Long operatorId = request.getOperatorId();

        if(!customerDAO.existsById(customerId)) throw new CustomerNotFoundException();

        if(!roomDAO.existsById(roomId)) throw new RoomNotFoundException();

        if(!employeeDAO.existsById(operatorId)) throw new EmployeeNotExistsException();

        stayRecordDAO.checkIn(customerId, roomId, operatorId);
    }

    public void book(BookRequest request) throws RuntimeException {

    }
}
