package com.vsu.demo.entity;

import java.math.BigDecimal;
import java.util.UUID;

public record Profile(UUID id, String login, BigDecimal balance) {
}