package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Suggestion {

    private String value;
    private SuggestionData data;

    public String getValue() {
        return value;
    }

    public SuggestionData getData() {
        return data;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setData(SuggestionData data) {
        this.data = data;
    }
}