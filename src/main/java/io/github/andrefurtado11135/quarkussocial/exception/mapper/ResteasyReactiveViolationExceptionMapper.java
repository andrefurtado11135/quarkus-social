package io.github.andrefurtado11135.quarkussocial.exception.mapper;

import io.github.andrefurtado11135.quarkussocial.vo.ApplicationErrorVO;
import io.github.andrefurtado11135.quarkussocial.vo.ErrorVO;
import io.quarkus.hibernate.validator.runtime.jaxrs.ResteasyReactiveViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ResteasyReactiveViolationExceptionMapper implements ExceptionMapper<ResteasyReactiveViolationException> {

    @Override
    public Response toResponse(ResteasyReactiveViolationException exception) {
        ApplicationErrorVO errorVO = new ApplicationErrorVO();
        errorVO.setErrors(exception.getConstraintViolations().stream().map(x -> new ErrorVO(x.getMessageTemplate())).collect(Collectors.toList()));
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(errorVO).build();
    }
}
