package petStore.Requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreRequest {
    private long petId;
    private long quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    @Override
    public String toString() {
        return ("petId : " + this.getPetId() + ", quantity : " + this.getQuantity() + ", shipDate : " + this.getShipDate() + ", status : " + this.getStatus() + ", complete : " + this.isComplete());
    }
}
