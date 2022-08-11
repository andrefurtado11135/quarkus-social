package io.github.andrefurtado11135.quarkussocial.exception;

public class ForbiddenRequestException extends RuntimeException{

    public ForbiddenRequestException(String message){
        super(message);
    }
}
