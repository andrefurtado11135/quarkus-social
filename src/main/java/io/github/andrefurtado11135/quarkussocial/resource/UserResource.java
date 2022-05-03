package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.exception.RequestValidationException;
import io.github.andrefurtado11135.quarkussocial.service.UserService;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource implements ApplicationResource{

    private UserService userService;

    private Validator validator;

    @Inject
    public UserResource(UserService userService, Validator validator){
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public Validator getValidator(){
        return this.validator;
    }

    @POST
    public Response createUser(CreateUserRequest request){
        validateRequest(request);
        return Response.status(Response.Status.CREATED).entity(userService.save(request)).build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok(userService.getAll()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@RestPath Long id){
        userService.deleteUser(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@RestPath Long id, CreateUserRequest createUserRequest){
        validateRequest(createUserRequest);
        userService.updateUser(id, createUserRequest);
        return Response.ok().build();
    }
}
