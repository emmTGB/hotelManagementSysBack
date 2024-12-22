package com.oxlxs.hotelmanagementsysback.dto.response;

import com.oxlxs.hotelmanagementsysback.entity.BookRecord;
import com.oxlxs.hotelmanagementsysback.entity.Customer;
import com.oxlxs.hotelmanagementsysback.entity.Room;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookRecordResponse {
    private Long id;
    private String customerName;
    private LocalDateTime bookTime;
    private LocalDateTime outTime;
    private Long roomId;
    private String roomType;
    private String phone;

    public BookRecordResponse(BookRecord bookRecord) {
        Customer customer = bookRecord.getCustomer();
        this.customerName = customer.getName();
        this.phone = customer.getPhone();
        Room room = bookRecord.getRoom();
        this.roomId = room.getId();
        this.roomType = room.getType().getName();
        this.id = bookRecord.getId();
        this.bookTime = bookRecord.getBookTime();
        this.outTime = bookRecord.getBookTime().plusDays(bookRecord.getBookDays());
    }
}
