package petStore.StepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import net.thucydides.core.annotations.Steps;
import petStore.APIs.PetAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class PetStepDefinitions {
    @Getter
    public long petId;
    @Steps
    PetAPI petAPI;

    @When("we hit the pet endpoint with the following details")
    public void weHitThePetEndpointWithTheFollowingDetails(DataTable dataTable) throws JsonProcessingException {
        petAPI.petDetails(dataTable);
    }

    @Then("the pet should be created successfully")
    public void thePetShouldBeCreatedSuccessfully() {
        restAssuredThat(response -> response.statusCode(200));
        petAPI.petCreated();
        petId = petAPI.getPetId();
    }
}
