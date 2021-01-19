package com.github.comrada.forwarder.config;

import com.github.comrada.forwarder.publisher.Publisher;
import com.github.comrada.forwarder.reader.Reader;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ReaderProperties.class)
public class ForwarderConfig {

    @Bean
    ConnectorsFactory connectorsFactory(ReaderProperties readerProperties) {
        return new ConnectorsFactory(readerProperties);
    }

    @Bean
    Reader reader(ConnectorsFactory connectorsFactory) {
        return new Reader(connectorsFactory.getConnector());
    }

    @Bean
    Publisher publisher(ConnectorsFactory connectorsFactory) {
        return new Publisher(connectorsFactory.getSender());
    }
}
