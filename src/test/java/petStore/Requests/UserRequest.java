package petStore.Requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    @Override
    public String toString() {
        return ("username : " + this.getUsername() + ", firstName : " + this.getFirstName() + ", lastName : " + this.getLastName() + ", email : " + this.getEmail() + ", password : " + this.getPassword() + ", phone : " + this.getPhone());
    }
}
