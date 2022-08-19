package io.github.andrefurtado11135.quarkussocial.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class FollowersPerUserResponseVO {

    private Integer followersCount;

    private List<FollowerResponse> content;
}
