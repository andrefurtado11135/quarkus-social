package io.github.andrefurtado11135.quarkussocial.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestValidationException extends RuntimeException{
    private List<String> validationErrors;

    public RequestValidationException(List<String> errors){
        super();
        this.validationErrors = errors;
    }
}
