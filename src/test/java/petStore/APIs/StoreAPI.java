package petStore.APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.rest.SerenityRest;
import petStore.Requests.StoreRequest;
import petStore.Responses.StoreResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static utilities.helpers.isLong;

@Getter
@Setter
public class StoreAPI extends PetAPI {
    private static final Logger logger = Logger.getLogger("Store");
    private long orderId;
    private long petId;
    private long quantity;
    private String shipDate;
    private String status;
    private boolean complete;
    private StoreRequest storeRequest;
    private StoreResponse storeResponse;

    public void createOrder(DataTable dataTable) throws JsonProcessingException {
        this.petId = super.petId;
        List<List<String>> data = dataTable.asLists();
        for (int i = 1; i < data.size(); i++) {
            quantity = Objects.isNull(data.get(i).get(0)) ? 0 : Long.parseLong(data.get(i).get(0));
            if (Objects.nonNull(data.get(i).get(1)) && data.get(i).get(1).contentEquals("TODAY"))
                shipDate = String.valueOf(Instant.now());
            else shipDate = null;
            status = Objects.isNull(data.get(i).get(2)) ? null : data.get(i).get(2);
            complete = !Objects.isNull(data.get(i).get(3)) && Boolean.parseBoolean(data.get(i).get(3));
        }
        storeRequest = StoreRequest.builder().petId(petId).quantity(quantity).shipDate(shipDate).status(status).complete(complete).build();
        logger.info("Store request is : " + new ObjectMapper().writeValueAsString(storeRequest));
        storeResponse = SerenityRest.given().baseUri("https://petstore.swagger.io").basePath("/v2/store/order").body(storeRequest, ObjectMapperType.GSON).accept(ContentType.JSON).contentType(ContentType.JSON).post().getBody().as(StoreResponse.class);
        logger.info("Store response is : " + new ObjectMapper().writeValueAsString(storeResponse));
    }

    public void orderPlaced() {
        //id
        assertThat(isLong(String.valueOf(storeResponse.getId()))).withFailMessage("Expected id is of long but it is : " + storeResponse.getId()).isTrue();
        logger.info("***id is of long as expected.***");
        //petId
        assertThat(isLong(String.valueOf(storeResponse.getPetId()))).withFailMessage("Expected pet id is of long but it is : " + storeResponse.getPetId()).isTrue();
        logger.info("***pet id is of long as expected.***");
        //quantity
        assertThat(storeResponse.getQuantity() >= 0).withFailMessage("Expected quantity is of integer but it is : " + storeResponse.getQuantity()).isTrue();
        logger.info("***quantity is of integer as expected.***");
        //shipDate
        assertThat(Instant.now().until(Instant.parse(storeResponse.getShipDate().split("\\+")[0] + 'Z'), ChronoUnit.SECONDS) < 5).withFailMessage("Expected shipDate timestamp withing 5 seconds but it is not : " + storeResponse.getShipDate()).isTrue();
        logger.info("***shipDate is within 5 seconds period as expected.***");
        //status
        assertThat(storeResponse.getStatus().contentEquals(status)).withFailMessage("Expected status is of " + status + " but it is : " + storeResponse.getStatus()).isTrue();
        logger.info("***status is of " + status + " as expected.***");
        //complete
        assertThat(complete && storeResponse.isComplete()).withFailMessage("Expected complete is of " + complete + " but it is : " + storeResponse.isComplete()).isTrue();
        logger.info("***complete is of " + complete + " as expected.***");
        orderId = storeResponse.getId();
    }
}
