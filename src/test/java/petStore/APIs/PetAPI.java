package petStore.APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.rest.SerenityRest;
import petStore.Requests.PetRequest;
import petStore.Responses.PetResponse;

import java.util.*;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static utilities.helpers.isLong;

@Getter
@Setter
public class PetAPI {
    private static final Logger logger = Logger.getLogger("Pet");
    protected long petId;
    private String categoryName;
    private String petName;
    private List<String> photoUrls = new ArrayList<>();
    private List<PetRequest.CategoryOrTag> tagsName = new ArrayList<>();
    private String status;
    private PetRequest petRequest;
    private PetResponse petResponse;

    public void petDetails(DataTable dataTable) throws JsonProcessingException {
        List<List<String>> data = dataTable.asLists();
        for (int i = 1; i < data.size(); i++) {
            categoryName = Objects.isNull(data.get(i).get(0)) ? null : data.get(i).get(0);
            petName = Objects.isNull(data.get(i).get(1)) ? null : data.get(i).get(1);
            if (Objects.nonNull(data.get(i).get(2))) {
                if (data.get(i).get(2).contains(";")) photoUrls = Arrays.asList(data.get(i).get(2).split(";"));
                else photoUrls = Collections.singletonList(data.get(i).get(2));
            } else photoUrls = null;
            if (Objects.nonNull(data.get(i).get(3))) {
                if (data.get(i).get(3).contains(";")) {
                    for (String tagName : data.get(i).get(3).split(";")) {
                        tagsName.add(PetRequest.CategoryOrTag.builder().name(tagName).build());
                    }
                } else tagsName.add(PetRequest.CategoryOrTag.builder().name(data.get(i).get(3)).build());
            } else tagsName = null;
            status = Objects.isNull(data.get(i).get(4)) ? null : data.get(i).get(4);
        }
        petRequest = PetRequest.builder().category(PetRequest.CategoryOrTag.builder().name(categoryName).build()).name(petName).photoUrls(photoUrls).tags(tagsName).status(status).build();
        logger.info("Pet request is : " + new ObjectMapper().writeValueAsString(petRequest));
        petResponse = SerenityRest.given().baseUri("https://petstore.swagger.io").basePath("/v2/pet").body(petRequest, ObjectMapperType.GSON).accept(ContentType.JSON).contentType(ContentType.JSON).post().getBody().as(PetResponse.class);
        logger.info("Pet response is : " + new ObjectMapper().writeValueAsString(petResponse));
    }

    public void petCreated() {
        //id
        assertThat(isLong(String.valueOf(petResponse.getId()))).withFailMessage("Expected id is of long but it is : " + petResponse.getId()).isTrue();
        logger.info("***id is of long as expected.***");
        //category - id
        assertThat(petResponse.getCategory().getId() >= 0).withFailMessage("Expected category id is of integer but it is : " + petResponse.getCategory().getId()).isTrue();
        logger.info("***category id is of integer as expected.***");
        //category - name
        assertThat(petResponse.getCategory().getName().contentEquals(categoryName)).withFailMessage("Expected category name is of " + categoryName + " but it is : " + petResponse.getCategory().getName()).isTrue();
        logger.info("***category name is of " + categoryName + " as expected.***");
        //name
        assertThat(petResponse.getName().contentEquals(petName)).withFailMessage("Expected name is of " + petName + " but it is : " + petResponse.getName()).isTrue();
        logger.info("***name is of " + petName + " as expected.***");
        //photoUrls
        assertThat(petResponse.getPhotoUrls().equals(photoUrls)).withFailMessage("Expected photoUrls is of " + photoUrls + " but it is : " + petResponse.getPhotoUrls()).isTrue();
        logger.info("***photoUrls is of " + photoUrls + " as expected.***");
        //tags
        assertThat(petResponse.getTags().equals(tagsName)).withFailMessage("Expected tags are of " + tagsName + " but it is : " + petResponse.getTags()).isTrue();
        logger.info("***tags are of " + tagsName + " as expected.***");
        //status
        assertThat(petResponse.getStatus().contentEquals(status)).withFailMessage("Expected status is of " + status + " but it is : " + petResponse.getStatus()).isTrue();
        logger.info("***status is of " + status + " as expected.***");
        petId = petResponse.getId();
    }
}
