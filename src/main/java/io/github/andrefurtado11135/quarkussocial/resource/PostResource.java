package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.model.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.service.PostService;
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

@Path("users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Posts")
public class PostResource{

    private PostService postService;

    @Inject
    public PostResource(PostService postService){
        this.postService = postService;
    }

    @Operation(summary = "Create a new post")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "201", description = "User created"), @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @POST
    public Response savePost(@NotNull @RestPath("userId") Long id, @Valid CreatePostRequest request){
        postService.savePost(id, request);
        return Response.status(Response.Status.CREATED).build();
    }

    @Operation(summary = "List posts")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Posts listed"), @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "400", description = "Follower Id invalid"),
                    @APIResponse(responseCode = "403", description = "You are not allowed to see this user's posts"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @GET
    public Response listPosts(@NotNull @RestPath("userId") Long id, @NotNull @HeaderParam("followerId") Long followerId){
        return Response.ok(postService.getPosts(id, followerId)).build();
    }
}
