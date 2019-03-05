package stepdef;

import io.restassured.http.ContentType;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BDDStyleMethods {

    public static void GETSimplePost(String postNumber){
        given().contentType(ContentType.JSON)
                .when().get(String.format("http://localhost:3000/posts/%s",postNumber))
                .then().body("author",is("typicode"));
    }

    public static void performContainsCollection(){
        given().contentType(ContentType.JSON)
                .when().get("http://localhost:3000/posts").
                then().body("author",containsInAnyOrder("Java","typicode")).statusCode(200);
    }

    public static void performPathParam(){
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParam("post","1").
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author",is("typicode"));
    }

    public static void performQueryParam(){
        given().
                contentType(ContentType.JSON).
                queryParam("id","1").
        when()
                .get("http://localhost:3000/posts").
        then()
                .body("author",hasItem("typicode"));
    }

    public static void postOperation(){
        HashMap<String,String> postContent = new HashMap<>();
        postContent.put("id","3");
        postContent.put("title","Robotium");
    postContent.put("author","KK");

        given().contentType(ContentType.JSON).
        with().
                body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author",is("KK"));
    }
}
