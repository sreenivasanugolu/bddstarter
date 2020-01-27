package net.bddtrader.practiceStepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;

import static org.assertj.core.api.Assertions.assertThat;

public class MarketPricesStepDefinitions {

    @Before
    public void setBaseURL(){
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @Given("^(?:.*) is an active trader on IEX$")
    public void patricia_is_an_active_trader_on_IEX() {}

    @Given("^(.*) is currently trading at (.*)$")
    public void currently_trading_at(String symbol, String expectedPrice) {
        RestAssured.given().body(expectedPrice)
                .contentType("application/json")
                .when().get("/stock/{symbol}/price", symbol)
                .then().statusCode(200);
    }

    @When("^(.*) requests the latest price for (.*)$")
    public void patricia_requests_the_latest_price_for_AAPL(String actor, String symbol) {
        SerenityRest.when().get("/stock/{symbol}/price", symbol)
                .then().statusCode(200);
    }

    @Then("^s?he should see a price of (.*)$")
    public void she_should_see_a_price_of(double expectedPrice) {
        Double price = SerenityRest.lastResponse().as(Double.class);
        assertThat(price).isEqualTo(expectedPrice);
    }

    @Given("^the market is closed$")
    public void the_market_is_closed() {
        RestAssured.get("/market/close");
    }


    @Given("^(.*) closed at (.*)$")
    public void closed_at(String symbol, int closingPrice) {
       RestAssured.given().body(closingPrice)
               .contentType("application/json")
               .when().post("/stock/{symbol}/closingprice", symbol)
               .then().statusCode(200);
    }




}
