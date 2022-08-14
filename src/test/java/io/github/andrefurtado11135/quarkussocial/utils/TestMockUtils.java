package io.github.andrefurtado11135.quarkussocial.utils;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.entity.Post;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.vo.PostResponseVO;

import java.time.LocalDateTime;

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
}
