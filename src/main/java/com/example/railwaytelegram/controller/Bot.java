package com.example.railwaytelegram.controller;

import com.example.railwaytelegram.entity.UserEntity;
import com.example.railwaytelegram.repository.UserEntityRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        if (msg != null && msg.hasText()) {
            User user = msg.getFrom();
            Long userId = user.getId();
            createOrUpdateUser(user);
            String msgText = msg.getText();
            if (msgText.equals("/start")) {
                SendMessage message = new SendMessage();
                message.setChatId(userId);
                message.setText("Нажмите на кнопку Сайт");
                execute(message);
            }
        }
    }

    private void createOrUpdateUser(User user) {
        Long userId = user.getId();
        UserEntity entity = userEntityRepository.findById(userId).orElse(null);
        if(entity == null) {
            entity = new UserEntity();
            entity.setId(userId);
            entity.setStartedAt(LocalDateTime.now());
        }
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setUserName(entity.getUserName());
        userEntityRepository.saveAndFlush(entity);
    }
}
