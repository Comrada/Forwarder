package com.github.comrada.forwarder.connector.slack;

import com.github.comrada.forwarder.connector.Sender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "reader.target", havingValue = "SLACK")
@EnableConfigurationProperties(SlackProperties.class)
public class SlackAutoConfiguration {

    @Bean(destroyMethod = "close")
    Sender slackConnector(SlackProperties slackProperties) {
        return new SlackConnector(slackProperties);
    }
}
