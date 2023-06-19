package petStore.StepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import petStore.APIs.StoreAPI;

public class StoreStepDefinitions {
    @Steps
    StoreAPI storeAPI;

    @When("we hit the store endpoint with the following details")
    public void weHitTheStoreEndpointWithTheFollowingDetails(DataTable dataTable) throws JsonProcessingException {
        storeAPI.createOrder(dataTable);
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        storeAPI.orderPlaced();
    }
}
