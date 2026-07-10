package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse {

    private List<Suggestion> suggestions;


    public List<Suggestion> getSuggestions() {
        return suggestions;
    }
}