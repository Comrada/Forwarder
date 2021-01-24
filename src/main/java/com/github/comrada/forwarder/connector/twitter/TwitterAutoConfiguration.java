package com.github.comrada.forwarder.connector.twitter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${reader.source}' == 'TWITTER' or '${reader.target}' == 'TWITTER'")
@EnableConfigurationProperties(TwitterProperties.class)
public class TwitterAutoConfiguration {

    @Bean
    TwitterConnector twitterConnector(TwitterProperties twitterProperties) {
        return new TwitterConnector(twitterProperties);
    }
}
