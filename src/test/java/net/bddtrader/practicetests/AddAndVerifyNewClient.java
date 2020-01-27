package net.bddtrader.practicetests;

import cucumber.api.java.eo.Se;
import io.restassured.RestAssured;
import net.bddtrader.clients.Client;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class AddAndVerifyNewClient {

    @Before
    public void setBaseURL() {
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @Test
    public void post_using_json() throws IOException {
        File jsonFile = new File("src/test/resources/templates/client.json");
        FileInputStream fis = new FileInputStream(jsonFile);
        SerenityRest.given().contentType("application/json")
                .and()
                .body(IOUtils.toString(fis,"UTF-8"))
                .when()
                .post("/client");
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");
        ClientDetails clientDetails = SerenityRest.get("/client/{clientId}", clientId).as(ClientDetails.class);
        assertThat(clientDetails.getFirstName()).isEqualTo("sree");
    }

    @Test
    public void test_using_jsonFile(){
        File jsonFile = new File("src/test/resources/templates/client.json");
        SerenityRest.given().contentType("application/json").body(jsonFile).post("/client");
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");
        ClientDetails clientDetails = SerenityRest.get("/client/{clientId}", clientId).as(ClientDetails.class);
        assertThat(clientDetails.getFirstName()).isEqualTo("sree");

    }

    @Test
    public void add_and_verify_new_Client() {
        Client newClient = Client.withFirstName("ruchi").andLastName("raj").andEmail("ruchi@test.com");
        SerenityRest.given().contentType("application/json")
                .and().body(newClient)
                .when().post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
        .time(lessThan(1000L));
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");
        ClientDetails clientDetails = SerenityRest.get("/client/{clientId}", clientId).as(ClientDetails.class);
        assertThat(clientDetails.getId()).isGreaterThan(0);
        assertThat(clientDetails.getFirstName()).isEqualTo("ruchi");
    }

    @Test
    public void delete_a_client_and_verify(){
        Client newClient = Client.withFirstName("pavan").andLastName("kumar").andEmail("pavan@test.com");
        SerenityRest.given().contentType("application/json")
                .and().body(newClient)
                .when().post("/client")
                .then().statusCode(200);
        String clientId = SerenityRest.lastResponse().jsonPath().getString("id");
        SerenityRest
                .given().pathParam("id", clientId)
                .when().delete("/client/{id}")
                .then().statusCode(204);

        SerenityRest
                .given()
                .pathParam("id", clientId)
                .when().get("client/{id}")
                .then().statusCode(404);
    }
}
