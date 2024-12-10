package com.oxlxs.hotelmanagementsysback.dto.response;


import com.oxlxs.hotelmanagementsysback.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerInfoResponse {
    public CustomerInfoResponse(Customer customer) {
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.idNumber = customer.getIdNumber();
        this.deposit = customer.getDeposit();
        this.roomNumber = customer.getStayRecord().getRoom().getNumber();
    }

    private String name;
    private String phone;
    private String idNumber;
    private String roomNumber;
    private BigDecimal deposit;
}
