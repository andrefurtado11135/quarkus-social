package io.github.andrefurtado11135.quarkussocial.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class CreatePostRequest {

    @NotBlank(message = "Text is required")
    private String text;
}
