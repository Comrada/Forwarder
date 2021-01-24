package com.github.comrada.forwarder.connector.telegram;

import com.github.comrada.forwarder.connector.Sender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "reader.target", havingValue = "TELEGRAM")
@EnableConfigurationProperties(TelegramProperties.class)
public class TelegramAutoConfiguration {

    @Bean(destroyMethod = "close")
    Sender telegramConnector(TelegramProperties telegramProperties,
            ThrottlingBot throttlingBot) throws TelegramApiException {
        return new TelegramConnector(telegramProperties, throttlingBot);
    }

    @Bean
    ThrottlingBot throttlingBot(TelegramProperties telegramProperties, TaskScheduler scheduler) {
        return new ThrottlingBot(telegramProperties, scheduler);
    }
}
