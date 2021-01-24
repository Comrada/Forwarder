package com.github.comrada.forwarder.connector.twitter.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiPath {
    String value() default "";

    HttpMethod method() default HttpMethod.GET;

    enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }
}
