package com.github.comrada.forwarder.connector.twitter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reader.twitter")
public class TwitterProperties {
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
