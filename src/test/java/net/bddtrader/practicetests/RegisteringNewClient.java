package net.bddtrader.practicetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(SerenityRunner.class)
public class RegisteringNewClient {

    @Before
    public void setBaseURL(){

        RestAssured.baseURI = "http://localhost:8080/api";
    }
    @Test
    public void should_return_a_new_client_Id(){
        String newClient = "{\n" +
                "  \"email\": \"shiree@test.com\",\n" +
                "  \"firstName\": \"shiree\",\n" +
                "   \"lastName\": \"anu\"\n" +
                "}";

       /* String newClient = "{\n" +
                "  \"email\": \"sree@test.com\",\n" +
                "  \"firstName\": \"sree\",\n" +
                "   \"lastName\": \"anu\"\n" +
                "}";*/

        //String newClient = Client.ClientBuilder()

       /* given().contentType("application/json")
                .body(newClient)
                .when().post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)));*/

        //String clientId = SerenityRest.lastResponse().jsonPath().getString("id");

       /* given().pathParam("id", clientId)
                .when().get("/client/{id}")
                .then().statusCode(200)
                .and().body("email", equalTo("shiree@test.com"))
                .and().body("firstName", equalTo("shiree"))
                .and().body("lastName", equalTo("anu"));*/

                when().get("/clients")
                .then().statusCode(200);

                List<ClientDetails> clientDetails = (List<ClientDetails>) SerenityRest.get("/clients").as(ClientDetails.class);

        clientDetails.forEach(
                individualDetails -> {
                    assertThat(individualDetails.getId()).isGreaterThan(0);
                    assertThat(individualDetails.getFirstName()).endsWith("ee");
                }
        );


    }

    @Test
    public void all_results_verify(){
        String newClient = "{\n" +
                "  \"email\": \"shiree@test.com\",\n" +
                "  \"firstName\": \"shiree\",\n" +
                "   \"lastName\": \"anu\"\n" +
                "}";
    }

    @Test
    public void result_should_succeed(){
        when().get("http://localhost:8080/api/client/1")
                .then().statusCode(200);
    }

    @Test
    public void verify_details(){
        when().get("/client/1")
                .then().statusCode(200);

        Ensure.that("fist name is returned", response -> response.body("firstName", equalTo("sree")));
        Ensure.that("email is returned", response -> response.body("email", equalTo("sree@test.com")));
    }

    @Test
    public void refactor_the_test_case(){
        given().pathParam("clientId", 1).
        when().get("/client/{clientId}")
                .then().statusCode(200);
        Ensure.that("name", response -> response.body("firstName", equalTo("sree")));
        Ensure.that("email", response -> response.body("email", equalTo("sree@test.com")));
    }

    @Test
    public void verify_all_registered_users_as_list(){
        given().contentType("application/json");
        when().get("/clients")
                .then().statusCode(200);
        List<ClientDetails> clientDetails = SerenityRest.lastResponse().jsonPath()
                .getList("clientDetails", ClientDetails.class);
        String id = lastResponse().jsonPath().getString("id");
        System.out.println(id);
        clientDetails.forEach(
                entity -> {
                    System.out.println(entity.getId());
                    System.out.println(entity.getFirstName());
                    System.out.println(entity.getLastName());
                    System.out.println(entity.getEmail());
                }
        );
        // System.out.println("Id is : " + individualDetails.getId());
        clientDetails.forEach(
                individualDetails -> {
                    assertThat(individualDetails.getId()).isGreaterThan(0);
                    assertThat(individualDetails.getFirstName()).endsWith("ee");
                }
        );

    }


}
