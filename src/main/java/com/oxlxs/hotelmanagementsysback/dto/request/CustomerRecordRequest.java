package com.oxlxs.hotelmanagementsysback.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRecordRequest {
    @NotBlank(message = "Invalid customer name!")
    private String name;

    private String phone;
    private String idNumber;

    private int status;
}
