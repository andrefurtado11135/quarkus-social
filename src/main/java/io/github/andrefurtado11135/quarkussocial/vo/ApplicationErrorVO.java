package io.github.andrefurtado11135.quarkussocial.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationErrorVO<T> {

    public T errors;

    public LocalDateTime timestamp;

    public ApplicationErrorVO(){
        this.timestamp = LocalDateTime.now();
    }
}
