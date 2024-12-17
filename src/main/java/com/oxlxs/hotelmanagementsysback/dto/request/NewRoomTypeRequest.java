package com.oxlxs.hotelmanagementsysback.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NewRoomTypeRequest {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String fullName;
    @NotNull
    BigDecimal price;
}
