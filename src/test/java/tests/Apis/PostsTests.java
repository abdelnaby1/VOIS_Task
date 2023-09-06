package tests.Apis;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utils.Helper.getRandomNumberBetweenTwoValues;

public class PostsTests {
    int randomIndex = getRandomNumberBetweenTwoValues(0,99);
    int postId;
    @Test
    public void checkGettingRandomPostId() {
        var res = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts");

        res.then().statusCode(HttpStatus.SC_OK);
        postId = res.jsonPath().getInt("["+randomIndex+"].id");

    }
    @Test(dependsOnMethods = {"checkGettingRandomPostId"})
    public void checkGettingPostComments(){
        var res = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/"+postId+"/comments");

        res.then().statusCode(HttpStatus.SC_OK);
        System.out.println(res.asPrettyString());

    }


}
