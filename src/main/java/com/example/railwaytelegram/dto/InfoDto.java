package com.example.railwaytelegram.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InfoDto {
    BigDecimal totalSum;
    BigDecimal inSum;
    BigDecimal outSum;
}
