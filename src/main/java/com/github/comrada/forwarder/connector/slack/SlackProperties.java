package com.github.comrada.forwarder.connector.slack;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reader.slack")
public class SlackProperties {
    private String token;
    private String channel;
    private String botIcon;
    private String botName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBotIcon() {
        return botIcon;
    }

    public void setBotIcon(String botIcon) {
        this.botIcon = botIcon;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
}
