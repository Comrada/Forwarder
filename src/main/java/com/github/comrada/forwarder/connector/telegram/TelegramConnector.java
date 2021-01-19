package com.github.comrada.forwarder.connector.telegram;

import com.github.comrada.forwarder.config.ReaderProperties;
import com.github.comrada.forwarder.connector.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public final class TelegramConnector implements Sender {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ReaderProperties.Telegram telegramProperties;

    public TelegramConnector(ReaderProperties.Telegram telegramProperties) {
        this.telegramProperties = telegramProperties;
    }

    @Override
    public void send(Collection<String> messages) {
        LOGGER.info("I'm TelegramConnector and I'm sending messages...");
    }
}
