package io.github.andrefurtado11135.quarkussocial.resource;

import io.github.andrefurtado11135.quarkussocial.exception.EntityNotFoundException;
import io.github.andrefurtado11135.quarkussocial.service.UserService;
import io.github.andrefurtado11135.quarkussocial.utils.TestMockUtils;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@QuarkusTest
class UserResourceTest {

    @InjectMock
    private UserService userService;

    @Inject
    private UserResource userResource;

    @Test
    void createUser() {
        when(userService.save(any())).thenReturn(TestMockUtils.mockedUser());

        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createUserRequest())
                .when().post("/users")
                .then().extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Test
    void createUserErrorBodyInvalid() {
        var request = TestMockUtils.createUserRequest();
        request.setName(null);

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/users")
                .then().extract().response();

        assertEquals(400, response.getStatusCode());
    }

    @Test
    void listAllUsers() {
        when(userService.getAll()).thenReturn(Arrays.asList(TestMockUtils.mockedUser()));

        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createUserRequest())
                .when().get("/users")
                .then().extract().response();
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void updateUser() {
        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createUserRequest())
                .when().put("/users/2")
                .then().extract().response();
        assertEquals(204, response.getStatusCode());
    }

    @Test
    void updateUserErrorBodyInvalid() {
        var request = TestMockUtils.createUserRequest();
        request.setAge(null);

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().put("/users/2")
                .then().extract().response();
        assertEquals(400, response.getStatusCode());
    }

    @Test
    void updateUserErrorNotFound() {
        doThrow(new EntityNotFoundException("User not found")).when(userService).updateUser((any()), any());

        var response = given()
                .contentType(ContentType.JSON)
                .body(TestMockUtils.createUserRequest())
                .when().put("/users/2")
                .then().extract().response();
        assertEquals(404, response.getStatusCode());
    }

    @Test
    void deleteUser() {
        var response = given()
                .contentType(ContentType.JSON)
                .when().delete("/users/2")
                .then().extract().response();
        assertEquals(204, response.getStatusCode());
    }

    @Test
    void deleteUserErrorNotFound() {
        doThrow(new EntityNotFoundException("User not found")).when(userService).deleteUser((any()));

        var response = given()
                .contentType(ContentType.JSON)
                .when().delete("/users/2")
                .then().extract().response();
        assertEquals(204, response.getStatusCode());
    }
}