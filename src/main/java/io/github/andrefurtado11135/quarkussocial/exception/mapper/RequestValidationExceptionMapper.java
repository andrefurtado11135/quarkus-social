package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.RequestValidationException;
import io.github.andrefurtado11135.quarkussocial.type.ApplicationErrorStatus;
import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class RequestValidationExceptionMapper implements ExceptionMapper<RequestValidationException> {

    @Override
    public Response toResponse(RequestValidationException exception) {
        ApplicationErrorVO<List<String>> errorVO = new ApplicationErrorVO<>();
        errorVO.setStatus(ApplicationErrorStatus.MISSING_REQUIRED_FIELDS.name());
        errorVO.setErrors(exception.getValidationErrors());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorVO).build();
    }
}
