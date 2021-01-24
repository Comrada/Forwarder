package com.github.comrada.forwarder.connector.telegram;

import static java.util.Objects.requireNonNull;

import com.github.comrada.forwarder.connector.Sender;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Collection;

public final class TelegramConnector implements Sender {
    private final TelegramProperties telegramProperties;
    private final ThrottlingBot telegramBot;

    public TelegramConnector(TelegramProperties telegramProperties,
            ThrottlingBot telegramBot) throws TelegramApiException {
        this.telegramProperties = requireNonNull(telegramProperties);
        this.telegramBot = requireNonNull(telegramBot);
        new TelegramBotsApi(DefaultBotSession.class).registerBot(this.telegramBot);
    }

    @Override
    public void send(Collection<String> messages) {
        if (messages.isEmpty()) return;
        messages.forEach(message -> telegramBot.send(message, telegramProperties.getChatId()));
    }
}
