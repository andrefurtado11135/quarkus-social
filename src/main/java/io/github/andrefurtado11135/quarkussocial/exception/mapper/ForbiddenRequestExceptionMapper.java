package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.ForbiddenRequestException;
import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ForbiddenRequestExceptionMapper implements ExceptionMapper<ForbiddenRequestException> {

    @Override
    public Response toResponse(ForbiddenRequestException exception) {
        ApplicationErrorVO<String> errorVO = new ApplicationErrorVO<>();
        errorVO.setErrors(exception.getMessage());
        return Response.status(Response.Status.FORBIDDEN.getStatusCode()).entity(errorVO).build();
    }
}