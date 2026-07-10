package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAddressResponse {

    private Location location;


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {

        private String value;
        private Data data;


        public String getValue() {
            return value;
        }


        public void setValue(String value) {
            this.value = value;
        }


        public Data getData() {
            return data;
        }


        public void setData(Data data) {
            this.data = data;
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        private String country;
        private String city;


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