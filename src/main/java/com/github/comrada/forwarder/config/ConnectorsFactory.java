package com.github.comrada.forwarder.config;

import com.github.comrada.forwarder.connector.Connector;
import com.github.comrada.forwarder.connector.Sender;
import com.github.comrada.forwarder.connector.slack.SlackConnector;
import com.github.comrada.forwarder.connector.telegram.TelegramConnector;
import com.github.comrada.forwarder.connector.twitter.TwitterConnector;

public final class ConnectorsFactory {
    private final ReaderProperties readerProperties;
    private Connector connector;
    private Sender sender;

    public ConnectorsFactory(ReaderProperties readerProperties) {
        this.readerProperties = readerProperties;
    }

    public Connector getConnector() {
        if (connector == null) {
            connector = switch (readerProperties.getSource()) {
                case TWITTER -> new TwitterConnector(readerProperties.getTwitter());
                default -> throw new IllegalStateException("Unsupported connector type: " + readerProperties.getTarget());
            };
        }
        return connector;
    }

    public Sender getSender() {
        if (sender == null) {
            sender = switch (readerProperties.getTarget()) {
                case TELEGRAM -> new TelegramConnector(readerProperties.getTelegram());
                case SLACK -> new SlackConnector(readerProperties.getSlack());
                default -> throw new IllegalStateException("Unsupported connector type: " + readerProperties.getTarget());
            };
        }
        return sender;
    }
}
