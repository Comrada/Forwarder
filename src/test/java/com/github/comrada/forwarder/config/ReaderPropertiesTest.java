package com.github.comrada.forwarder.config;

import static com.github.comrada.forwarder.config.ReaderProperties.ConnectorType.SLACK;
import static com.github.comrada.forwarder.config.ReaderProperties.ConnectorType.TWITTER;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReaderPropertiesTest {
    @Autowired
    ReaderProperties properties;

    @Test
    void testIfOk() {
        assertEquals("0 * * * * ?", properties.getCron());
        assertEquals(TWITTER, properties.getSource());
        assertEquals(SLACK, properties.getTarget());
        ReaderProperties.Twitter twitter = properties.getTwitter();
        assertEquals("twitter-api-key", twitter.getApiKey());
        assertEquals("twitter-api-secret", twitter.getApiSecret());
        assertEquals("elonmusk", twitter.getUsername());
        assertEquals(5, twitter.getTweetsToRead());
        ReaderProperties.Slack slack = properties.getSlack();
        assertEquals("slack-token", slack.getToken());
        assertEquals("updates", slack.getChannel());
        assertEquals(":robot_face:", slack.getBotIcon());
        assertEquals("Twitter", slack.getBotName());
    }
}