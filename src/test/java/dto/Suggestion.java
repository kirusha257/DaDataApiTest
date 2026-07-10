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
}