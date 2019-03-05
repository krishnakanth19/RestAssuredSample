package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {
    public static RequestSpecification request;
    public RestAssuredExtension(){
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri("http://localhost:3000");
        request = RestAssured.given().spec(builder.build());

    }

    public static ResponseOptions<Response> getOpsWithPathParams(String uri, Map<String,String> pathParams) throws URISyntaxException {
        request.params(pathParams);
        return request.get(uri);
    }

    public static ResponseOptions<Response> getOps(String uri) {
        try {
            return request.get(new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> postOpsWithBodyAndPathParam(String uri, Map<String,String> pathParam,Map<String,String> postParam){
        request.pathParams(pathParam);
        request.body(postParam);
        return request.post(uri);
    }

    public static ResponseOptions<Response> deleteOps(String uri, Map<String,String> pathParams){
        request.pathParams(pathParams);
        return request.delete(uri);
    }

    public static ResponseOptions<Response> postOpsWithBody(String uri,Map<String, String> body){
        request.body(body);
        return request.post(uri);
    }
}
