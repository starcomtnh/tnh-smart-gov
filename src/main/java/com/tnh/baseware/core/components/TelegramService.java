package com.tnh.baseware.core.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@ConditionalOnProperty(prefix = "baseware.core.chat-bot.telegram", name = "enabled", havingValue = "true")
public class TelegramService extends TelegramLongPollingBot {

    @Value("${baseware.core.chat-bot.telegram.username}")
    private String botUsername;

    @Value("${baseware.core.chat-bot.telegram.bot-token}")
    private String botToken;
    @Value("${baseware.core.chat-bot.telegram.chat-id}")
    private String chatId;

    public TelegramService(@Value("${baseware.core.chat-bot.telegram.bot-token}") String botToken) {
        super(botToken);
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Không xử lý gì cả vì bot chỉ gửi tin nhắn đi
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    // Hàm để gửi tin nhắn
    public void sendMessage(String message) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(message);
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace(); // hoặc logger
        }
    }
}
