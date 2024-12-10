package com.oxlxs.hotelmanagementsysback.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegisterRequest {
    @NotBlank(message = "Invalid customer name!")
    private String name;

    @NotBlank
    private String password;

    private String phone;
    private String idNumber;
}
