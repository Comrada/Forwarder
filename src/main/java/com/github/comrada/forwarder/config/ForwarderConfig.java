package com.github.comrada.forwarder.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ReaderProperties.class)
public class ForwarderConfig {

}
