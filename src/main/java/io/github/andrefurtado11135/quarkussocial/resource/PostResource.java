package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.service.PostService;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource{

    private PostService postService;

    @Inject
    public PostResource(PostService postService){
        this.postService = postService;
    }

    @POST
    public Response savePost(@NotNull @RestPath("userId") Long id, @Valid CreatePostRequest request){
        postService.savePost(id, request);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts(@NotNull @RestPath("userId") Long id, @NotNull @HeaderParam("followerId") Long followerId){
        return Response.ok(postService.getPosts(id, followerId)).build();
    }
}
