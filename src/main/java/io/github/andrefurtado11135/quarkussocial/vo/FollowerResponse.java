package io.github.andrefurtado11135.quarkussocial.vo;

import io.github.andrefurtado11135.quarkussocial.entity.Follower;
import lombok.Data;

@Data
public class FollowerResponse {

    private Long id;

    private String name;

    public FollowerResponse() {
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FollowerResponse(Follower follower){
        this(follower.getId(), follower.getFollower().getName());
    }
}
