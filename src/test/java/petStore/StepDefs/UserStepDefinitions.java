package petStore.StepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import petStore.APIs.UserAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class UserStepDefinitions {

    @Steps
    UserAPI userAPI;

    @When("we hit the user endpoint with the following details")
    public void weHitTheUserEndpointWithTheFollowingDetails(DataTable dataTable) throws JsonProcessingException {
        userAPI.userDetails(dataTable);
    }

    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        restAssuredThat(response -> response.statusCode(200));
        userAPI.userCreated();
    }

    @When("we hit the user endpoint for login with the following details")
    public void weHitTheUserEndpointForLoginWithTheFollowingDetails(DataTable dataTable) throws JsonProcessingException {
        userAPI.loginUser(dataTable);
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        userAPI.successLogin();
    }

    @When("we hit the user endpoint for user {string}")
    public void weHitTheUserEndpointForUser(String username) throws JsonProcessingException {
        userAPI.fetchUser(username);
    }

    @Then("the {string} error message should be thrown")
    public void theErrorMessageShouldBeThrown(String error) {
        userAPI.errorMessage(error);
    }
}
