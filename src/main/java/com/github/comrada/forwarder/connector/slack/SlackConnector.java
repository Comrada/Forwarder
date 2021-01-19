package com.github.comrada.forwarder.connector.slack;

import com.github.comrada.forwarder.config.ReaderProperties;
import com.github.comrada.forwarder.connector.Sender;

import java.util.Collection;

public final class SlackConnector implements Sender {
    private final ReaderProperties.Slack slackProperties;

    public SlackConnector(ReaderProperties.Slack slackProperties) {
        this.slackProperties = slackProperties;
    }

    @Override
    public void send(Collection<String> messages) {
        //TODO
    }
}
