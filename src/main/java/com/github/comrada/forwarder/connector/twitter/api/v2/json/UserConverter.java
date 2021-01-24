package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.github.comrada.forwarder.connector.twitter.model.User;

public final class UserConverter {

    private UserConverter() {
    }

    public static User convert(UserData response) {
        if (response == null) return null;
        User user = new User();
        user.setId(response.getId());
        user.setUsername(response.getUsername());
        user.setName(response.getName());
        user.setDescription(response.getDescription());
        user.setLocation(response.getLocation());
        user.setUrl(response.getUrl());
        user.setProfileImageUrl(response.getProfileImageUrl());
        user.setCreatedAt(response.getCreatedAt());
        return user;
    }
}
