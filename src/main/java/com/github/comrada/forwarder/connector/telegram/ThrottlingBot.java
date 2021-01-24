package com.github.comrada.forwarder.connector.telegram;

import static java.util.Objects.isNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThrottlingBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TELEGRAM_SENDING_RATE = 1100;
    private final TelegramProperties properties;
    private final Queue<Distribution> distributions = new ConcurrentLinkedQueue<>();

    public ThrottlingBot(TelegramProperties properties, TaskScheduler scheduler) {
        this.properties = properties;
        scheduler.scheduleAtFixedRate(this::handleQueue, TELEGRAM_SENDING_RATE);
    }

    @Override
    public String getBotUsername() {
        return properties.getBotName();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }

    @Override
    public void onUpdateReceived(final Update update) {
        Message message = update.getMessage();
        if (!isNull(message)) {
            LOGGER.info("User sent a message: {}, in a chat: {}", message.getText(), message.getChatId());
        }
    }

    private void handleQueue() {
        Distribution distribution = distributions.poll();
        if (distribution != null) {
            SendMessage sendMessageCommand = new SendMessage();
            sendMessageCommand.setChatId(distribution.chat);
            sendMessageCommand.setText(distribution.message);
            try {
                execute(sendMessageCommand);
            } catch (TelegramApiException e) {
                LOGGER.error("Failed to send a message", e);
            }
        }
    }

    public void send(String message, String chat) {
        distributions.offer(new Distribution(message, chat));
    }
}
