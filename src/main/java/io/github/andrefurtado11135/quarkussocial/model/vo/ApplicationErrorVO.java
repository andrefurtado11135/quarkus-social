package io.github.andrefurtado11135.quarkussocial.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApplicationErrorVO {

    public List<ErrorVO> errors;

    public LocalDateTime timestamp;

    public ApplicationErrorVO(){
        this.timestamp = LocalDateTime.now();
    }
}
