package com.example.railwaytelegram.dto;

import com.example.railwaytelegram.entity.UserOperationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OperationHistoryDto {
    Long id;
    BigDecimal sum;
    String category;
    String createdAt;

    public OperationHistoryDto(UserOperationEntity entity) {
        this.id = entity.getId();
        this.sum = entity.getSum();
        this.category = entity.getCategory();
        this.createdAt = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
