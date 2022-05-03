package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.ApplicationException;
import io.github.andrefurtado11135.quarkussocial.type.ApplicationErrorStatus;
import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(ApplicationException exception) {
        ApplicationErrorVO<String> errorVO = new ApplicationErrorVO<>();
        errorVO.setStatus(exception.getErrorStatus());
        errorVO.setErrors(exception.getMessage());
        return Response.status(exception.getHttpStatus()).entity(errorVO).build();
    }
}