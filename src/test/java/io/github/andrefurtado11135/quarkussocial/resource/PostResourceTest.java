package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.exception.EntityNotFoundException;
import io.github.andrefurtado11135.quarkussocial.exception.ForbiddenRequestException;
import io.github.andrefurtado11135.quarkussocial.exception.InvalidParamException;
import io.github.andrefurtado11135.quarkussocial.service.PostService;
import io.github.andrefurtado11135.quarkussocial.utils.TestMockUtils;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@QuarkusTest
public class PostResourceTest {

    @InjectMock
    private PostService postService;

    @Inject
    private PostResourceTest postResource;

    @Test
    void createPost() {
        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createPostRequest())
                .when().post("/posts/2")
                .then().extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Test
    void createPostErrorUserNotFound() {
        doThrow(new EntityNotFoundException("User not found")).when(postService).savePost((any()), any());

        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createPostRequest())
                .when().post("/posts/2")
                .then().extract().response();

        assertEquals(404, response.getStatusCode());
    }

    @Test
    void createPostErrorBodyInvalid() {
        CreatePostRequest request = TestMockUtils.createPostRequest();
        request.setText("");

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/posts/2")
                .then().extract().response();

        assertEquals(400, response.getStatusCode());
    }

    @Test
    void listAllPosts(){
        when(postService.getPosts(any(), any())).thenReturn(Arrays.asList(TestMockUtils.mockedPostResponse()));

        Header header = new Header("followerId", "1");

        var response = given()
                .contentType(ContentType.JSON)
                .header(header)
                .when().get("/posts/2")
                .then().extract().response();

        assertEquals(200, response.getStatusCode());
    }

    @Test
    void listAllPostsErrorUserNotFound(){
        when(postService.getPosts(any(), any())).thenThrow(new EntityNotFoundException("User not found"));

        Header header = new Header("followerId", "1");

        var response = given()
                .contentType(ContentType.JSON)
                .header(header)
                .when().get("/posts/2")
                .then().extract().response();

        assertEquals(404, response.getStatusCode());
    }

    @Test
    void listAllPostsErrorFollowerInvalid(){
        when(postService.getPosts(any(), any())).thenThrow(new InvalidParamException(("Header param followerId not found")));

        Header header = new Header("followerId", "1");

        var response = given()
                .contentType(ContentType.JSON)
                .header(header)
                .when().get("/posts/2")
                .then().extract().response();

        assertEquals(400, response.getStatusCode());
    }

    @Test
    void listAllPostsErrorForbidden(){
        when(postService.getPosts(any(), any())).thenThrow(new ForbiddenRequestException("You are not allowed to see this user's posts"));

        Header header = new Header("followerId", "1");

        var response = given()
                .contentType(ContentType.JSON)
                .header(header)
                .when().get("/posts/2")
                .then().extract().response();

        assertEquals(403, response.getStatusCode());
    }
}
