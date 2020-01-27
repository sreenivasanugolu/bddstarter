package net.bddtrader.practicetests;

import io.restassured.RestAssured;
import net.bddtrader.clients.Client;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


public class AddAndVerifySecondClient {
    Client client;
    @Before
    public void setBaseURL(){
        RestAssured.baseURI = "http://localhost:8080/api";
         client = Client.withFirstName("kalyan").andLastName("kumar").andEmail("kalyan@test.com");
    }

    @Test
    public void test1(){

        SerenityRest.given()
                .contentType("application/json")
                .body(client)
                .when().post("/client")
                .then().statusCode(200);
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");
        ClientDetails clientDetails = SerenityRest.get("/client/{clientId}", clientId).as(ClientDetails.class);
        System.out.println(clientDetails.getFirstName().toUpperCase());
        System.out.println(clientDetails.getLastName().toUpperCase());
        assertThat(clientDetails.getFirstName()).isEqualTo("kalyan");
    }

    @Test
    public void test2(){
        File jsonFile = new File("src/test/resources/templates/client.json");
        SerenityRest.given()
                .contentType("application/json")
                .body(jsonFile)
                .post("/client")
                .then().statusCode(200);
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");


    }

}
