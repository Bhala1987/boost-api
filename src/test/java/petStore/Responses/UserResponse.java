package petStore.Responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserResponse {
    private int code;
    private String type;
    private String message;

    @Override
    public boolean equals(Object o) {
        UserResponse response = (UserResponse) o;
        return (response.code == this.code && !Objects.nonNull(response.type) || response.type.contentEquals(this.type)) && (!Objects.nonNull(response.message) || response.message.contentEquals(this.message));
    }

    @Override
    public String toString() {
        return ("code : " + this.getCode() + ", type : " + this.getType() + ", message : " + this.getMessage());
    }
}
