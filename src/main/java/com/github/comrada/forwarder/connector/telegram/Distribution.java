package com.github.comrada.forwarder.connector.telegram;

import java.util.Objects;

public class Distribution {
    public final String message;
    public final String chat;

    public Distribution(String message, String chat) {
        this.message = message;
        this.chat = chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribution that = (Distribution) o;
        return message.equals(that.message) && chat.equals(that.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, chat);
    }
}
