package tests.Apis;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class UserTests {
    int userId;
    @Test
    public void checkGettingFirstUserID() {
        var res = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        res.then().statusCode(HttpStatus.SC_OK);
        userId = res.jsonPath().getInt("[0].id");

    }
    @Test(dependsOnMethods = {"checkGettingFirstUserID"})
    public void checkGettingUserAlbumTitle(){
        var res = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/"+userId+"/albums");
        res.then()
                .statusCode(HttpStatus.SC_OK);

        List<String> albumsTitle = res.jsonPath().getList("title");

        boolean anyMatch = albumsTitle.stream()
                .anyMatch(title ->  title.length() > 300);

        Assert.assertFalse(anyMatch,"There are one or more title exceed 300 characters");

    }
    @Test(dependsOnMethods = {"checkGettingFirstUserID"})
    public void checkCreateAPostWithoutTitleAndBody(){
        String title = "some title";
        String body = "some description";
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId",userId);
        jsonObj.put("title",title);
        jsonObj.put("body",body);

         given()
                .contentType(ContentType.JSON)
                .body(jsonObj.toJSONString())
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .body("userId",Matchers.equalTo(userId))
                .and()
                .body("title",Matchers.equalTo(title))
                .and()
                .body("body",Matchers.equalTo(body));
    }
    @Test(dependsOnMethods = {"checkGettingFirstUserID"})
    public void checkGettingTitlesOfNotCompletedTodos(){
        var res =
                given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/"+userId+"/todos");

        res.then()
                .statusCode(HttpStatus.SC_OK);

        List<Map<String, Object>> objects =  res.getBody().as(new TypeRef<>() {});
        List<Map<String, Object>> matchedObjects = objects.stream().filter((Map<String, Object> object) -> object.get("completed").equals(false))
                .peek(System.out::println)
                .toList();
        System.out.println("the titles of not completed todos are: ");
        matchedObjects.forEach(obj -> System.out.println(obj.get("title")));
    }
}
