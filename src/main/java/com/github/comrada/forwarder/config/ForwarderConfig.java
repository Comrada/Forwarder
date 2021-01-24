package com.github.comrada.forwarder.config;

import com.github.comrada.forwarder.cache.HashCache;
import com.github.comrada.forwarder.cache.PublishingCache;
import com.github.comrada.forwarder.connector.Connector;
import com.github.comrada.forwarder.connector.Sender;
import com.github.comrada.forwarder.maintenance.Maintenance;
import com.github.comrada.forwarder.maintenance.Publisher;
import com.github.comrada.forwarder.maintenance.Reader;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(ReaderProperties.class)
public class ForwarderConfig {

    @Bean
    Reader reader(Connector connector) {
        return new Reader(connector);
    }

    @Bean
    Publisher publisher(PublishingCache<String> cache) {
        Sender consoleWriter = messages -> messages.forEach(System.out::println);
        return new Publisher(consoleWriter, cache);
    }

    @Bean
    Maintenance maintenance(TaskScheduler scheduler, Reader reader, Publisher publisher, ReaderProperties properties) {
        return new Maintenance(scheduler, reader, publisher, properties);
    }

    @Bean
    PublishingCache<String> publishingCache() {
        return new HashCache<>(Duration.ofDays(10));
    }
}
