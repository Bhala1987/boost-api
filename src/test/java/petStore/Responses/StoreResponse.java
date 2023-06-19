package petStore.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse extends UserResponse {
    private long id;
    private long petId;
    private long quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    @Override
    public boolean equals(Object o) {
        StoreResponse response = (StoreResponse) o;
        return (response.id == this.id && response.petId == this.petId && response.quantity == this.quantity && !Objects.nonNull(response.shipDate) || response.shipDate.contentEquals(this.shipDate)) && (!Objects.nonNull(response.status) || response.status.contentEquals(this.status) && response.complete == this.complete);
    }

    @Override
    public String toString() {
        return ("id : " + this.getId() + ", petId : " + this.getPetId() + ", quantity : " + this.getQuantity() + ", shipDate : " + this.getShipDate() + ", status : " + this.getStatus() + ", complete : " + this.isComplete());
    }
}
