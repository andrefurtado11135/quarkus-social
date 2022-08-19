package io.github.andrefurtado11135.quarkussocial.utils;

import io.github.andrefurtado11135.quarkussocial.model.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.model.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.model.dto.FollowerRequest;
import io.github.andrefurtado11135.quarkussocial.model.entity.User;
import io.github.andrefurtado11135.quarkussocial.model.vo.FollowerResponse;
import io.github.andrefurtado11135.quarkussocial.model.vo.FollowersPerUserResponseVO;
import io.github.andrefurtado11135.quarkussocial.model.vo.PostResponseVO;

import java.time.LocalDateTime;
import java.util.Arrays;

public class TestMockUtils {

    public static CreateUserRequest createUserRequest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setName("ABCDXYZ");
        request.setAge(20);
        return request;
    }

    public static User mockedUser(){
        User user = new User();
        user.setName("ABCDXYZ");
        user.setAge(20);
        return user;
    }

    public static CreatePostRequest createPostRequest(){
        CreatePostRequest request = new CreatePostRequest();
        request.setText("TESTE");
        return request;
    }

    public static PostResponseVO mockedPostResponse(){
        PostResponseVO postResponseVO = new PostResponseVO();
        postResponseVO.setText("TESTE123");
        postResponseVO.setDateTime(LocalDateTime.now());
        return postResponseVO;
    }

    public static FollowerRequest followerRequest(){
        FollowerRequest followerRequest = new FollowerRequest();
        followerRequest.setFollowerId(3L);
        return followerRequest;
    }

    public static FollowersPerUserResponseVO mockedFollower(){
        FollowersPerUserResponseVO followers = new FollowersPerUserResponseVO();
        followers.setFollowersCount(1);
        followers.setContent(Arrays.asList(mockedFollowerResponse()));
        return followers;
    }

    public static FollowerResponse mockedFollowerResponse(){
        FollowerResponse response = new FollowerResponse();
        response.setId(1L);
        response.setName("TESTE456");
        return response;
    }
}
