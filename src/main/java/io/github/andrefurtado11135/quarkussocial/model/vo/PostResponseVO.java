package io.github.andrefurtado11135.quarkussocial.model.vo;

import io.github.andrefurtado11135.quarkussocial.model.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseVO {

    private String text;

    private LocalDateTime dateTime;

    public static PostResponseVO fromEntity(Post post){
        var response = new PostResponseVO();
        response.setText(post.getText());
        response.setDateTime(post.getDateTime());
        return response;
    }
}
