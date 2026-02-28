package com.vsu.demo.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public record CreateProfileRequest(String login, BigDecimal balance) {
}
