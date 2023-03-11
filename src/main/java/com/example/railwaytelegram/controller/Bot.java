package com.example.railwaytelegram.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

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
            String msgText = msg.getText();
            if (msgText.equals("/start")) {
                SendMessage message = new SendMessage();
                message.setChatId(userId);
                message.setText("Нажмите на кнопку");
                message.setReplyMarkup(new InlineKeyboardMarkup(List.of(List.of(
                        InlineKeyboardButton.builder().text("Открыть").callbackData("Open").build()
                ))));
                execute(message);
            }
        }
    }
}
