package petStore.Responses;

import lombok.Getter;
import lombok.Setter;
import petStore.Requests.PetRequest;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PetResponse {
    private long id;
    private PetRequest.CategoryOrTag category;
    private String name;
    private List<String> photoUrls;
    private List<PetRequest.CategoryOrTag> tags;
    private String status;

    @Override
    public boolean equals(Object o) {
        PetResponse response = (PetResponse) o;
        return (response.id == this.id && !Objects.nonNull(response.category) || response.category.equals(this.category)) && (!Objects.nonNull(response.name) || response.name.contentEquals(this.name) && (!Objects.nonNull(response.photoUrls) || response.photoUrls.equals(this.photoUrls)) && (!Objects.nonNull(response.tags) || response.tags.equals(this.tags)) && (!Objects.nonNull(response.status) || response.status.contentEquals(this.status)));
    }

    @Override
    public String toString() {
        return ("id : " + this.getId() + ", category : " + this.getCategory() + ", name : " + this.getName() + ", photoUrls : " + this.getPhotoUrls() + ", tags : " + this.getTags() + ", status : " + this.getStatus());
    }
}
