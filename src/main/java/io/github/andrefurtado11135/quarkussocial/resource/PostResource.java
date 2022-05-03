package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.service.PostService;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource implements ApplicationResource{

    private PostService postService;

    private Validator validator;

    @Inject
    public PostResource(PostService postService, Validator validator){
        this.postService = postService;
        this.validator = validator;
    }

    @Override
    public Validator getValidator() {
        return this.validator;
    }

    @POST
    public Response savePost(@RestPath("userId") Long id, CreatePostRequest request){
        validateRequest(request);
        postService.savePost(id, request);
        return Response.status(201).build();
    }

    @GET
    public Response listPosts(@RestPath("userId") Long id){

        return Response.ok(postService.getPosts(id)).build();
    }
}
