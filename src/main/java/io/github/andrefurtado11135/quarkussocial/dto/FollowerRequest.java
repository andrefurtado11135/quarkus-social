package io.github.andrefurtado11135.quarkussocial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FollowerRequest {

    @NotNull(message = "follower_id is required")
    @JsonProperty("follower_id")
    public Long followerId;
}
