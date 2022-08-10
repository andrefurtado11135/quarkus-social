package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.FollowerRequest;
import io.github.andrefurtado11135.quarkussocial.service.FollowerService;
import io.github.andrefurtado11135.quarkussocial.vo.FollowerResponse;
import io.github.andrefurtado11135.quarkussocial.vo.FollowersPerUserResponseVO;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource{

    private FollowerService followerService;

    @Inject
    public FollowerResource(FollowerService followerService){
        this.followerService = followerService;
    }

    @PUT
    public Response followUser(@NotNull @RestPath("userId") Long id, @Valid FollowerRequest request){
        followerService.followUser(id, request);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listFollowers(@NotNull @RestPath("userId") Long id){
        List<FollowerResponse> followers = followerService.getFollowersByUser(id);
        FollowersPerUserResponseVO response = new FollowersPerUserResponseVO();
        response.setFollowersCount(followers.size());
        response.setContent(followers);
        return Response.ok(response).build();
    }

    @DELETE
    public Response unfollowUser(@NotNull @RestPath("userId") Long id, @NotNull @RestQuery("followerId") Long followerId){
        return Response.ok().build();
    }


}
