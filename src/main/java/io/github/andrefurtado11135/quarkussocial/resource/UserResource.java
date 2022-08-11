package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
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
@Tag(name = "Users")
public class UserResource{

    private UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @APIResponses(
	    value = {
            @APIResponse(responseCode = "201", description = "User created"), @APIResponse(responseCode = "400", description = "Request body invalid"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @POST
    public Response createUser(@Valid CreateUserRequest request){
        return Response.status(Response.Status.CREATED).entity(userService.save(request)).build();
    }

    @Operation(summary = "List all users")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Users listed"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @GET
    public Response listAllUsers(){
        return Response.ok(userService.getAll()).build();
    }

    @Operation(summary = "Delete an user")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "User deleted"),
                    @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @DELETE
    @Path("{id}")
    public Response deleteUser(@NotNull @RestPath Long id){
        userService.deleteUser(id);
        return Response.ok().build();
    }

    @Operation(summary = "Update an user")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "User updated"),
                    @APIResponse(responseCode = "400", description = "Request body invalid"),
                    @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @PUT
    @Path("{id}")
    public Response updateUser(@NotNull @RestPath Long id, @Valid CreateUserRequest createUserRequest){
        userService.updateUser(id, createUserRequest);
        return Response.ok().build();
    }
}
