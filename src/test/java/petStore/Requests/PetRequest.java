package petStore.Requests;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class PetRequest {
    private CategoryOrTag category;
    private String name;
    private List<String> photoUrls;
    private List<CategoryOrTag> tags;
    private String status;

    @Override
    public String toString() {
        return ("category : " + this.getCategory() + ", name : " + this.getName() + ", photoUrls : " + this.getPhotoUrls() + ", tags : " + this.getTags() + ", status : " + this.getStatus());
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryOrTag {
        private int id;
        private String name;

        @Override
        public boolean equals(Object o) {
            CategoryOrTag response = (CategoryOrTag) o;
            return (response.id == this.id && !Objects.nonNull(response.name) || response.name.equals(this.name));
        }

        @Override
        public String toString() {
            return ("id : " + this.getId() + ", name : " + this.getName());
        }
    }
}
