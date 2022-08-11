package io.github.andrefurtado11135.quarkussocial.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FollowerRequest {

    @NotNull(message = "follower_id is required")
    public Long followerId;
}
