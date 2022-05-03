package io.github.andrefurtado11135.quarkussocial.vo;

import lombok.Data;

@Data
public class ApplicationErrorVO<T> {

    public String status;

    public T errors;
}
