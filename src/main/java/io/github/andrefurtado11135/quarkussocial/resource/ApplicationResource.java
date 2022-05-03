package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.RequestDto;
import io.github.andrefurtado11135.quarkussocial.exception.ApplicationException;
import io.github.andrefurtado11135.quarkussocial.exception.RequestValidationException;
import io.github.andrefurtado11135.quarkussocial.type.ApplicationErrorStatus;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public interface ApplicationResource {

    Validator getValidator();

    default void validateRequest(RequestDto requestDto){
        if (requestDto == null){
            throw new ApplicationException("Request body should not be null", 422, ApplicationErrorStatus.NULL_REQUEST_BODY.name());
        }

        List<String> errors = new ArrayList<>();
        getValidator().validate(requestDto).forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()){
            throw new RequestValidationException(errors);
        }
    }

}
