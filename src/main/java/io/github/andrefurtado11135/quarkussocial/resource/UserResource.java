package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.service.UserService;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource{

    private UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @POST
    public Response createUser(@Valid CreateUserRequest request){
        return Response.status(Response.Status.CREATED).entity(userService.save(request)).build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok(userService.getAll()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@NotNull @RestPath Long id){
        userService.deleteUser(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@NotNull @RestPath Long id, @Valid CreateUserRequest createUserRequest){
        userService.updateUser(id, createUserRequest);
        return Response.ok().build();
    }
}
