package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.exception.InvalidFollowerException;
import io.github.andrefurtado11135.quarkussocial.model.vo.ApplicationErrorVO;
import io.github.andrefurtado11135.quarkussocial.model.vo.ErrorVO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFollowerExceptionMapper implements ExceptionMapper<InvalidFollowerException> {

    @Override
    public Response toResponse(InvalidFollowerException exception) {
        ApplicationErrorVO errorVO = new ApplicationErrorVO();
        errorVO.setErrors(ErrorVO.mapErrors(exception));
        return Response.status(Response.Status.CONFLICT.getStatusCode()).entity(errorVO).build();
    }
}
