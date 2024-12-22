package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.BookRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.CustomerRecordRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.BookRecordResponse;
import com.oxlxs.hotelmanagementsysback.entity.*;
import com.oxlxs.hotelmanagementsysback.exception.customer.CustomerBusyException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRecordDAO bookRecordDAO;

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private StayRecordDAO stayRecordDAO;
    @Autowired
    private RoomTypeDAO roomTypeDAO;

    public List<BookRecordResponse> findAll() throws RuntimeException {
        List<BookRecord> bookRecords = bookRecordDAO.findAll();
        return bookRecords.stream().map(BookRecordResponse::new).collect(Collectors.toList());
    }

    @Transactional(
            rollbackFor = RuntimeException.class
    )
    public void book(BookRequest request) throws RuntimeException {
        if(customerDAO.isBusy(request.getCustomer().getIdNumber())){
            throw new CustomerBusyException();
        }

        Customer customer = new Customer(request.getCustomer());
        customer.setStatus(2);
        customerDAO.save(customer);

        RoomType type = roomTypeDAO.findById(request.getTypeName()).orElseThrow(()->new RuntimeException("RoomType not found"));
        Room room = roomDAO.findFirstByTypeAndStatus(type, RoomStatus.AVAILABLE).orElseThrow(RoomNotFoundException::new);
        room.setStatus(RoomStatus.BOOKED);

        BookRecord bookRecord = new BookRecord();
        bookRecord.setCustomer(customer);
        bookRecord.setBookTime(LocalDateTime.now());
        bookRecord.setBookDays(request.getDays());
        bookRecord.setRoom(room);

        roomDAO.save(room);
        bookRecordDAO.save(bookRecord);

    }

    @Transactional(
            rollbackFor = RuntimeException.class
    )
    public void bookIn(Long id) throws RuntimeException {
        BookRecord bookRecord = bookRecordDAO.findById(id).orElseThrow(()->new RuntimeException("BookRecord not found"));

        Customer customer = bookRecord.getCustomer();
        Room room = bookRecord.getRoom();

        stayRecordDAO.checkin(LocalDateTime.now(), room.getId(), BigDecimal.ZERO, customer.getId(), customer.getIdNumber());

        bookRecordDAO.delete(bookRecord);
    }

    @Transactional(
            rollbackFor = RuntimeException.class
    )
    public void unBook(Long id) throws RuntimeException {
        BookRecord bookRecord = bookRecordDAO.findById(id).orElseThrow(()->new RuntimeException("BookRecord not found"));

        Customer customer = bookRecord.getCustomer();
        customer.setStatus(null);
        customerDAO.save(customer);

        Room room = bookRecord.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);
        roomDAO.save(room);

        bookRecordDAO.delete(bookRecord);
    }
}
