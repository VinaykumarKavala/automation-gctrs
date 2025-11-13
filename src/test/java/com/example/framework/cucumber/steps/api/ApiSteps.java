package com.example.framework.cucumber.steps.api;

import com.example.framework.api.RestClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiSteps {
    private final RestClient client = new RestClient();
    private Response last;

    @When("I GET {string}") public void iGet(String path){ last = client.get(path); }
    @When("I POST {string} with JSON:") public void iPostBody(String path, String json){ last = client.postJson(path, json); }

    @Then("status should be {int}") public void status(int code){ assertThat(last.statusCode(), is(code)); }
    @Then("status is one of {int},{int}") public void statusOne(int a,int b){ assertThat(last.statusCode(), anyOf(is(a), is(b))); }
    @Then("json path {string} is non-empty array") public void jpNonEmpty(String p){ assertThat(last.jsonPath().getList(p), not(empty())); }
    @Then("json path {string} equals {string}") public void jpEquals(String p,String v){ assertThat(String.valueOf(last.jsonPath().get(p)), equalTo(v)); }
}
