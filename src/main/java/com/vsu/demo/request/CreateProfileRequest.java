package com.vsu.demo.request;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public record CreateProfileRequest(
        @Size(min=3)
        String login,
        BigDecimal balance) {
}