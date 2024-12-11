package com.oxlxs.hotelmanagementsysback.dto.response;

import com.oxlxs.hotelmanagementsysback.entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private Long id;
    private String username;
    private String role;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.username = employee.getUsername();
        this.role = employee.getRole().name();
    }
}
