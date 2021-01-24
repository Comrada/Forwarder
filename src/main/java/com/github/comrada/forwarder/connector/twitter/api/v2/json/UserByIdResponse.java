package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.github.comrada.forwarder.connector.twitter.api.ApiPath;

@ApiPath("/users/%s?user.fields=created_at,description,location,profile_image_url,url,verified")
public final class UserByIdResponse extends UserResponse {
}
