package petStore.APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.rest.SerenityRest;
import petStore.Requests.UserRequest;
import petStore.Responses.UserResponse;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static utilities.helpers.base64toString;
import static utilities.helpers.isLong;

@Getter
@Setter
public class UserAPI {
    private static final Logger logger = Logger.getLogger("User");
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private long userId;
    private List<List<String>> data;

    public void userDetails(DataTable dataTable) throws JsonProcessingException {
        data = dataTable.asLists();
        for (int i = 1; i < data.size(); i++) {
            username = Objects.isNull(data.get(i).get(0)) ? null : data.get(i).get(0);
            firstName = Objects.isNull(data.get(i).get(1)) ? null : data.get(i).get(1);
            lastName = Objects.isNull(data.get(i).get(2)) ? null : data.get(i).get(2);
            email = Objects.isNull(data.get(i).get(3)) ? null : data.get(i).get(3);
            password = Objects.isNull(data.get(i).get(4)) ? null : base64toString(data.get(i).get(4));
            phone = Objects.isNull(data.get(i).get(5)) ? null : data.get(i).get(5);
        }
        userRequest = UserRequest.builder().username(username).firstName(firstName).lastName(lastName).email(email).password(password).phone(phone).build();
        logger.info("User request is : " + new ObjectMapper().writeValueAsString(userRequest));
        userResponse = SerenityRest.given().baseUri("https://petstore.swagger.io").basePath("/v2/user").body(userRequest, ObjectMapperType.GSON).accept(ContentType.JSON).contentType(ContentType.JSON).post().getBody().as(UserResponse.class);
        logger.info("User response is : " + new ObjectMapper().writeValueAsString(userResponse));
    }

    public void userCreated() {
        //code
        assertThat(userResponse.getCode() == 200).withFailMessage("Expected code is 200 but it is : " + userResponse.getCode()).isTrue();
        logger.info("***code is 200 as expected.***");
        //type
        assertThat(userResponse.getType().contentEquals("unknown")).withFailMessage("Expected type is unknown but it is : " + userResponse.getType()).isTrue();
        logger.info("***type is unknown as expected.***");
        //message
        assertThat(isLong(userResponse.getMessage())).withFailMessage("Expected message is of long but it is : " + userResponse.getMessage()).isTrue();
        logger.info("***message is of long value as expected.***");
        userId = Long.parseLong(userResponse.getMessage());
    }

    public void loginUser(DataTable dataTable) throws JsonProcessingException {
        data = dataTable.asLists();
        for (int i = 1; i < data.size(); i++) {
            username = Objects.isNull(data.get(i).get(0)) ? null : data.get(i).get(0);
            password = Objects.isNull(data.get(i).get(1)) ? null : base64toString(data.get(i).get(1));
        }
        userResponse = SerenityRest.given().baseUri("https://petstore.swagger.io").basePath("/v2/user/login").queryParam("username", username).queryParam("password", password).accept(ContentType.JSON).contentType(ContentType.JSON).get().getBody().as(UserResponse.class);
        logger.info("User response is : " + new ObjectMapper().writeValueAsString(userResponse));
    }

    public void successLogin() {
        //code
        assertThat(userResponse.getCode() == 200).withFailMessage("Expected code is 200 but it is : " + userResponse.getCode()).isTrue();
        logger.info("***code is 200 as expected.***");
        //type
        assertThat(userResponse.getType().contentEquals("unknown")).withFailMessage("Expected type is unknown but it is : " + userResponse.getType()).isTrue();
        logger.info("***type is unknown as expected.***");
        //message
        assertThat(userResponse.getMessage().contains("logged in user session:") && isLong(userResponse.getMessage().split(":")[1])).withFailMessage("Expected message to contain 'logged in user session:' along with long but it is : " + userResponse.getMessage()).isTrue();
        logger.info("***message contain 'logged in user session:' along with the long value as expected.***");
    }

    public void fetchUser(String username) throws JsonProcessingException {
        userResponse = SerenityRest.given().baseUri("https://petstore.swagger.io").basePath("/v2/user/{username}").pathParam("username", username).accept(ContentType.JSON).contentType(ContentType.JSON).get().getBody().as(UserResponse.class);
        logger.info("User response is : " + new ObjectMapper().writeValueAsString(userResponse));

    }

    public void errorMessage(String error) {
        //code
        assertThat(userResponse.getCode() == 1).withFailMessage("Expected code is 1 but it is : " + userResponse.getCode()).isTrue();
        logger.info("***code is 1 as expected.***");
        //type
        assertThat(userResponse.getType().contentEquals("error")).withFailMessage("Expected type is error but it is : " + userResponse.getType()).isTrue();
        logger.info("***type is error as expected.***");
        //message
        assertThat(userResponse.getMessage().contentEquals(error)).withFailMessage("Expected message is " + error + " but it is : " + userResponse.getMessage()).isTrue();
        logger.info("***message is " + error + " as expected.***");
    }
}
