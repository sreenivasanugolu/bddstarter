package net.bddtrader.practiceStepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.hamcrest.Matchers;


import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class JsonPlaceHolderSteps {

    Actor actor;

    @Before(order=1)
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        //actor = Actor.named("Tommy").whoCan(CallAnApi.at("https://jsonplaceholder.typicode.com"));
        actor = Actor.named("Tommy").whoCan(CallAnApi.at("http://localhost:8080/api"));
    }

    @Given("the stage is set for the user")
    public void the_stage_is_set_for_the_actor(){
        OnStage.setTheStage(new OnlineCast());
        actor = Actor.named("Tommy").whoCan(CallAnApi.at("http://localhost:8080/api"));
    }

    @Given("post data")
    public void post_data() {

        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("templates/client.json").toURI());

            actor.attemptsTo(
                    Post.to("/client")
                        .with(request -> request.header("Content-Type", "application/json")
                                                .body(path.toFile())
                        )
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    /*@Given("post data")
    public void post_data() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("jsondata/postData.json").toURI());

            actor.attemptsTo(
                    Post.to("/users")
                            .with(request -> request.header("Content-Type", "application/json")
                                    .body(path.toFile())
                            )
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }*/

    @When("success code is 201")
    public void successCode() {
        actor.should(
                seeThatResponse("The user should have been successfully added",
                        response -> response.statusCode(200))
        );

    }

    @Then("assert for name")
    public void id_is_created() {
        actor.should(
                seeThatResponse(response -> response
                        .body("firstName", Matchers.equalTo("sree")))
        );

    }

}





