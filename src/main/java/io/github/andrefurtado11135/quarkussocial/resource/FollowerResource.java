package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.FollowerRequest;
import io.github.andrefurtado11135.quarkussocial.service.FollowerService;
import io.github.andrefurtado11135.quarkussocial.vo.FollowerResponse;
import io.github.andrefurtado11135.quarkussocial.vo.FollowersPerUserResponseVO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
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
@Tag(name = "Followers")
public class FollowerResource{

    private FollowerService followerService;

    @Inject
    public FollowerResource(FollowerService followerService){
        this.followerService = followerService;
    }

    @Operation(summary = "Follow user")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Follow successful"),
                    @APIResponse(responseCode = "400", description = "Request invalid"),
                    @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @PUT
    public Response followUser(@NotNull @RestPath("userId") Long id, @Valid FollowerRequest request){
        followerService.followUser(id, request);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Operation(summary = "List followers")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Followers listed"),
                    @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @GET
    public Response listFollowers(@NotNull @RestPath("userId") Long id){
        List<FollowerResponse> followers = followerService.getFollowersByUser(id);
        FollowersPerUserResponseVO response = new FollowersPerUserResponseVO();
        response.setFollowersCount(followers.size());
        response.setContent(followers);
        return Response.ok(response).build();
    }

    @Operation(summary = "Unfollow user")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Unfollow successful"),
                    @APIResponse(responseCode = "404", description = "User not found"),
                    @APIResponse(responseCode = "500", description = "Internal Server Error")})
    @DELETE
    public Response unfollowUser(@NotNull @RestPath("userId") Long id, @NotNull @RestQuery("followerId") Long followerId){
        followerService.unfollowUser(id, followerId);
        return Response.noContent().build();
    }


}
