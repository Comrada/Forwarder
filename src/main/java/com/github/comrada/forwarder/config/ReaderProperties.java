package com.github.comrada.forwarder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reader")
public class ReaderProperties {
    private String cron;
    private ConnectorType source;
    private ConnectorType target;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
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

    enum ConnectorType {
        TWITTER,
        TELEGRAM,
        SLACK
    }
}
