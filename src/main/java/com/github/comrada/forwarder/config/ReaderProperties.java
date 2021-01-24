package com.github.comrada.forwarder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reader")
public class ReaderProperties {
    private String cron;
    private Telegram telegram;
    private ConnectorType source;
    private ConnectorType target;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public void setTelegram(Telegram telegram) {
        this.telegram = telegram;
    }

    public ConnectorType getSource() {
        return source;
    }

    public void setSource(ConnectorType source) {
        this.source = source;
    }

    public ConnectorType getTarget() {
        return target;
    }

    public void setTarget(ConnectorType target) {
        this.target = target;
    }

    public static final class Telegram {
        private String token;
        private String chatId;
        private String botName;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }

        public String getBotName() {
            return botName;
        }

        public void setBotName(String botName) {
            this.botName = botName;
        }
    }

    enum ConnectorType {
        TWITTER,
        TELEGRAM,
        SLACK
    }
}
