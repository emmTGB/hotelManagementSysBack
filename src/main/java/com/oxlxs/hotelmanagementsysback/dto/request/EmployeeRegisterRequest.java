package com.oxlxs.hotelmanagementsysback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRegisterRequest {
    private String username;
    private String password;
    private String role;
}
