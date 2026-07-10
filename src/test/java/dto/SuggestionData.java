package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionData {


    private String country;

    private String city;


    public String getCountry() {
        return country;
    }


    public String getCity() {
        return city;
    }
}