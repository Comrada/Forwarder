package com.github.comrada.forwarder.connector.twitter;

import com.github.comrada.forwarder.connector.Connector;
import com.github.comrada.forwarder.connector.twitter.api.ApiClient;
import com.github.comrada.forwarder.connector.twitter.api.v2.ApiClientImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnExpression("'${reader.source}' == 'TWITTER' or '${reader.target}' == 'TWITTER'")
@EnableConfigurationProperties(TwitterProperties.class)
public class TwitterAutoConfiguration {
    private static final String API_BASE_URL = "https://api.twitter.com/2/";

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        InMemoryReactiveOAuth2AuthorizedClientService authorizedClientService =
                new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations);
        ReactiveOAuth2AuthorizedClientProvider provider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager clientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations,
                        authorizedClientService);
        clientManager.setAuthorizedClientProvider(provider);
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauthFilter =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
        oauthFilter.setDefaultOAuth2AuthorizedClient(true);
        oauthFilter.setDefaultClientRegistrationId("twitter");
        return WebClient.builder()
                .baseUrl(API_BASE_URL)
                .filter(oauthFilter)
                .build();
    }

    @Bean(destroyMethod = "close")
    Connector twitterConnector(TwitterProperties twitterProperties, ApiClient apiClient) {
        return new TwitterConnector(twitterProperties, apiClient);
    }

    @Bean
    ApiClient apiClient(WebClient webClient) {
        return new ApiClientImpl(webClient);
    }
}
