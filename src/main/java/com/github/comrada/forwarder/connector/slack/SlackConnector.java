package com.github.comrada.forwarder.connector.slack;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import com.github.comrada.forwarder.connector.Sender;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collection;

public final class SlackConnector implements Sender {
    private static final Logger LOGGER = LogManager.getLogger();
    /** Slack API client */
    private final Slack slack;
    /** Slack properties */
    private final SlackProperties properties;

    public SlackConnector(SlackProperties slackProperties) {
        properties = requireNonNull(slackProperties);
        slack = Slack.getInstance();
    }

    @Override
    public void send(Collection<String> messages) {
        try {
            String message = joinMessages(messages);
            if (message.isBlank()) return;
            ChatPostMessageResponse response = slack
                    .methods(properties.getToken())
                    .chatPostMessage(req -> req
                            .channel(properties.getChannel())
                            .iconEmoji(properties.getBotIcon())
                            .username(properties.getBotName())
                            .text(message)
                    );
            if (!isNull(response.getError()))
                LOGGER.warn("Failed to send a message to Slack, error message: {}", response.getError());
        } catch (IOException | SlackApiException e) {
            LOGGER.error("Failed to send a message to Slack", e);
        }
    }

    @Override
    public void close() throws Exception {
        slack.close();
    }

    private String joinMessages(Collection<String> messages) {
        return String.join("\n", messages);
    }
}
