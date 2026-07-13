package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalResponse {
    private List<PostalSuggestion> suggestions;

    public List<PostalSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<PostalSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostalSuggestion {
        private String value;
        private PostalData data;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public PostalData getData() {
            return data;
        }

        public void setData(PostalData data) {
            this.data = data;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostalData {
        private String postalCode;
        private String country;
        private String city;

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}