package com.github.comrada.forwarder.config;

import static com.github.comrada.forwarder.config.ReaderProperties.ConnectorType.SLACK;
import static com.github.comrada.forwarder.config.ReaderProperties.ConnectorType.TWITTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.comrada.forwarder.TestConfig;
import com.github.comrada.forwarder.connector.slack.SlackProperties;
import com.github.comrada.forwarder.connector.twitter.TwitterProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@ContextConfiguration(classes = TestConfig.class)
@EnableConfigurationProperties({ReaderProperties.class, TwitterProperties.class, SlackProperties.class})
class ReaderPropertiesTest {
    @Autowired
    ReaderProperties readerProperties;
    @Autowired
    TwitterProperties twitterProperties;
    @Autowired
    SlackProperties slackProperties;

    @SpringBootApplication
    static class TestApp {
    }

    @Test
    void testIfOk() {
        assertEquals("0 * * * * ?", readerProperties.getCron());
        assertEquals(TWITTER, readerProperties.getSource());
        assertEquals(SLACK, readerProperties.getTarget());
        assertEquals("twitter-api-key", twitterProperties.getApiKey());
        assertEquals("twitter-api-secret", twitterProperties.getApiSecret());
        assertEquals("elonmusk", twitterProperties.getUsername());
        assertEquals(5, twitterProperties.getTweetsToRead());
        assertEquals("slack-token", slackProperties.getToken());
        assertEquals("updates", slackProperties.getChannel());
        assertEquals(":robot_face:", slackProperties.getBotIcon());
        assertEquals("Twitter", slackProperties.getBotName());
    }
}