package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.InvalidParamException;
import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidParamExceptionMapper implements ExceptionMapper<InvalidParamException> {

    @Override
    public Response toResponse(InvalidParamException exception) {
        ApplicationErrorVO<String> errorVO = new ApplicationErrorVO<>();
        errorVO.setErrors(exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorVO).build();
    }
}
