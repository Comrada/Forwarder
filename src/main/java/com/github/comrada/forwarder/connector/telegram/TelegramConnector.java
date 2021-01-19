package com.github.comrada.forwarder.connector.telegram;

import com.github.comrada.forwarder.config.ReaderProperties;
import com.github.comrada.forwarder.connector.Sender;

import java.util.Collection;

public final class TelegramConnector implements Sender {
    private final ReaderProperties.Telegram telegramProperties;

    public TelegramConnector(ReaderProperties.Telegram telegramProperties) {
        this.telegramProperties = telegramProperties;
    }

    @Override
    public void send(Collection<String> messages) {
        //TODO
    }
}
