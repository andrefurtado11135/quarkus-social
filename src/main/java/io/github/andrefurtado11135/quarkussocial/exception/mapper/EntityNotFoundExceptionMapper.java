package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.EntityNotFoundException;
import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        ApplicationErrorVO<String> errorVO = new ApplicationErrorVO<>();
        errorVO.setErrors(exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(errorVO).build();
    }
}
