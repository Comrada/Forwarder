package com.github.comrada.forwarder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reader")
public class ReaderProperties {
    private String cron;
    private Slack slack;
    private Twitter twitter;
    private Telegram telegram;
    private ConnectorType source;
    private ConnectorType target;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Slack getSlack() {
        return slack;
    }

    public void setSlack(Slack slack) {
        this.slack = slack;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
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

    public static final class Slack {
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

    public static final class Twitter {
        private String apiKey;
        private String apiSecret;
        private String username;
        private int tweetsToRead = 5;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getApiSecret() {
            return apiSecret;
        }

        public void setApiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getTweetsToRead() {
            return tweetsToRead;
        }

        public void setTweetsToRead(int tweetsToRead) {
            this.tweetsToRead = tweetsToRead;
        }
    }

    enum ConnectorType {
        TWITTER,
        TELEGRAM,
        SLACK
    }
}
