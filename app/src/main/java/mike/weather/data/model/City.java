package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("Key")
    private String key;
    @SerializedName("LocalizedName")
    private String name;
    @SerializedName("Country")
    private Country country;
    @SerializedName("AdministrativeArea")
    private AdministrativeArea area;

    public City(String key, String name, Country country, AdministrativeArea area) {
        this.key = key;
        this.name = name;
        this.country = country;
        this.area = area;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdministrativeArea getArea() {
        return area;
    }

    public void setArea(AdministrativeArea area) {
        this.area = area;
    }

    public static class Country {
        @SerializedName("LocalizedName")
        private String name;

        public Country(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class AdministrativeArea {
        @SerializedName("ID")
        private String id;

        public AdministrativeArea(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
