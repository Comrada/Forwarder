package com.github.comrada.forwarder.connector.slack;

import com.github.comrada.forwarder.config.ReaderProperties;
import com.github.comrada.forwarder.connector.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public final class SlackConnector implements Sender {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ReaderProperties.Slack slackProperties;

    public SlackConnector(ReaderProperties.Slack slackProperties) {
        this.slackProperties = slackProperties;
    }

    @Override
    public void send(Collection<String> messages) {
        LOGGER.info("I'm SlackConnector and I'm sending messages...");
    }
}
