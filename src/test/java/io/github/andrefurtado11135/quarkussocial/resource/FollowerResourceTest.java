package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.model.dto.FollowerRequest;
import io.github.andrefurtado11135.quarkussocial.exception.EntityNotFoundException;
import io.github.andrefurtado11135.quarkussocial.exception.InvalidFollowerException;
import io.github.andrefurtado11135.quarkussocial.service.FollowerService;
import io.github.andrefurtado11135.quarkussocial.utils.TestMockUtils;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(FollowerResource.class)
public class FollowerResourceTest {

    @InjectMock
    private FollowerService followerService;

    @Inject
    private FollowerResource followerResource;

    @Test
    void followUser(){
        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 2)
                .body(TestMockUtils.followerRequest())
                .when().put()
                .then().extract().response();

        assertEquals(204, response.getStatusCode());
    }

    @Test
    void followUserInvalidBody(){
        FollowerRequest request = TestMockUtils.followerRequest();
        request.setFollowerId(null);

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 2)
                .body(request)
                .when().put()
                .then().extract().response();

        assertEquals(400, response.getStatusCode());
    }

    @Test
    void followUserWithSameId(){
        doThrow(new InvalidFollowerException("You can not follow yourself")).when(followerService).followUser((any()), any());
        FollowerRequest request = TestMockUtils.followerRequest();
        request.setFollowerId(2L);

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 2)
                .body(request)
                .when().put()
                .then().extract().response();

        assertEquals(409, response.getStatusCode());
    }

    @Test
    void followUserNotFound(){
        doThrow(new EntityNotFoundException("User not found")).when(followerService).followUser((any()), any());

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 999)
                .body(TestMockUtils.followerRequest())
                .when().put()
                .then().extract().response();

        assertEquals(404, response.getStatusCode());
    }

    @Test
    void listFollowers(){
        when(followerService.getFollowersByUser(any())).thenReturn(TestMockUtils.mockedFollower());

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 2)
                .when().get()
                .then().extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test
    void listFollowersErrorUserNotFound(){
        when(followerService.getFollowersByUser(any())).thenThrow(new EntityNotFoundException("User not found"));

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 999)
                .when().get()
                .then().extract().response();

        assertEquals(404, response.getStatusCode());
    }

    @Test
    void unfollowUser(){
        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 2)
                .queryParam("followerId", 3)
                .when().delete()
                .then().extract().response();

        assertEquals(204, response.getStatusCode());
    }

    @Test
    void unfollowErrorUserNotFound(){
        doThrow(new EntityNotFoundException("User not found")).when(followerService).unfollowUser((any()), any());

        var response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 999)
                .queryParam("followerId", 3)
                .when().delete()
                .then().extract().response();

        assertEquals(404, response.getStatusCode());
    }
}
