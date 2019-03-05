package stepdef;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import utils.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class MyStepdefs {
    private static ResponseOptions<Response> response;
    @Given("^I perform get operation for \"([^\"]*)\"$")
    public void iPerformGetOperationFor(String uri) throws Throwable {
        response = RestAssuredExtension.getOps(uri);
    }

    @And("^I perform get for the post number \"([^\"]*)\"$")
    public void iPerformGetForThePostNumber(String postNumber) throws Throwable {
        BDDStyleMethods.GETSimplePost(postNumber);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String author) throws Throwable {
         assertThat(response.getBody().jsonPath().getList("author"),hasItem(author));
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethods.performContainsCollection();
    }

    @Then("^I should see the author name using path param$")
    public void iShouldSeeTheAuthorNameUsingPathParam() {
        BDDStyleMethods.performPathParam();
    }

    @Then("^I should see the author name using query param$")
    public void iShouldSeeTheAuthorNameUsingQueryParam() {
        BDDStyleMethods.performQueryParam();
    }

    @Given("^I perform post operation \"([^\"]*)\"$")
    public void iPerformPostOperation(String arg0) throws Throwable {
        BDDStyleMethods.postOperation();
    }

    @Given("^I perform post operation for \"([^\"]*)\"$")
    public void iPerformPostOperationFor(String uri, DataTable data) throws Throwable {
       List<List<String>> dt = data.raw();
        HashMap<String,String> body = new HashMap<>();
        body.put("name",dt.get(1).get(0));
        HashMap<String,String> path = new HashMap<>();
        path.put("profileNo",dt.get(1).get(1));
        response = RestAssuredExtension.postOpsWithBodyAndPathParam(uri,path,body);
    }

    @Then("^I should verify body has name as \"([^\"]*)\"$")
    public void iShouldVerifyBodyHasNameAs(String name) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"),is(name));
    }

    @Given("^perform post operations using body \"([^\"]*)\"$")
    public void performPostOperationsUsingBody(String uri, DataTable data) throws Throwable {
        List<List<String>> body = data.raw();
        HashMap<String,String> map = new HashMap<>();
        map.put("id",body.get(1).get(0));
        map.put("title",body.get(1).get(1));
        map.put("author",body.get(1).get(2));
        RestAssuredExtension.postOpsWithBody(uri,map);
    }

    @And("^perform delete operation for post \"([^\"]*)\"$")
    public void performDeleteOperationForPost(String uri,DataTable tbl) throws Throwable {
        List<List<String>> data = tbl.raw();
        HashMap<String,String> map = new HashMap<>();
        map.put("postId",data.get(1).get(0));
        RestAssuredExtension.deleteOps(uri,map);
    }

    @And("^perform get operation for \"([^\"]*)\"$")
    public void performGetOperationFor(String uri,DataTable tbl) throws Throwable {
        List<List<String>> data = tbl.raw();
        HashMap<String,String> map = new HashMap<>();
        map.put("postId",data.get(1).get(0));
        response = RestAssuredExtension.getOpsWithPathParams(uri,map);
    }

    @Then("^verify that post not exist \"([^\"]*)\"$")
    public void verifyThatPostNotExist(String title) throws Throwable {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
}
