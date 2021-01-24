package com.github.comrada.forwarder.connector.twitter.api.v2;

import com.github.comrada.forwarder.connector.twitter.api.ApiClient;
import com.github.comrada.forwarder.connector.twitter.api.ApiPath;
import com.github.comrada.forwarder.connector.twitter.api.ApiPath.HttpMethod;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.TweetConverter;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.TweetResponse;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.UserByIdResponse;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.UserByUsernameResponse;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.UserConverter;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.UserResponse;
import com.github.comrada.forwarder.connector.twitter.api.v2.json.UserTweetsResponse;
import com.github.comrada.forwarder.connector.twitter.model.Tweet;
import com.github.comrada.forwarder.connector.twitter.model.User;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ApiClientImpl implements ApiClient {
    private final WebClient webClient;

    public ApiClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public User getUserById(String id) {
        UserResponse response = doRequest(UserByIdResponse.class, List.of(id));
        return UserConverter.convert(response.getData());
    }

    @Override
    public User getUserByName(String userName) {
        UserResponse response = doRequest(UserByUsernameResponse.class, List.of(userName));
        return UserConverter.convert(response.getData());
    }

    @Override
    public Tweet getTweet(String id) {
        TweetResponse response = doRequest(TweetResponse.class, List.of(id));
        return TweetConverter.convert(response.getData());
    }

    @Override
    public Collection<Tweet> getLastUserTweets(String userId, int count) {
        if (count < 5 || count > 100) {
            throw new IllegalArgumentException("The `count` query parameter value must be between 5 and 100");
        }
        UserTweetsResponse response = doRequest(UserTweetsResponse.class, List.of(userId, count));
        return response.getData().stream().map(TweetConverter::convert).collect(Collectors.toList());
    }

    private <T> T doRequest(Class<T> responseClass, List<Object> params) {
        if (!responseClass.isAnnotationPresent(ApiPath.class)) {
            throw new RuntimeException("ApiPath annotation must be present on response class " +
                    responseClass.getSimpleName());
        }
        String apiPath = responseClass.getAnnotation(ApiPath.class).value();
        HttpMethod httpMethod = responseClass.getAnnotation(ApiPath.class).method();
        WebClient.RequestHeadersUriSpec<?> request = createRequestSpec(httpMethod);
        String url = String.format(apiPath, params.toArray());
        return request
                .uri(url)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }

    private WebClient.RequestHeadersUriSpec<?> createRequestSpec(HttpMethod method) {
        return switch (method) {
            case GET -> webClient.get();
            case PUT -> webClient.put();
            case POST -> webClient.post();
            case DELETE -> webClient.delete();
        };
    }
}
