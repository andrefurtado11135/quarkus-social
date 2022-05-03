package io.github.andrefurtado11135.quarkussocial.exception;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private int httpStatus;
    private String errorStatus;

    public ApplicationException(String message, int httpStatus, String errorStatus){
        super(message);
        this.httpStatus = httpStatus;
        this.errorStatus = errorStatus;
    }
}
