package com.example.railwaytelegram.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Пользователь
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "userEntity")
public class UserEntity {
    @Id
    Long id;
    String firstName;
    String lastName;
    String userName;
    LocalDateTime startedAt;
}
