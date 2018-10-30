package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class City {
    private String query;
    @SerializedName("name")
    private String cityName;
    @SerializedName("state")
    private String stateName;
    @SerializedName("countryFull")
    private String countryName;
    private Conditions currentConditions;

    public City(String query, String cityName, String stateName, String countryName) {
        this.query = query;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
    }

    public String getQuery() {
        return generateQuery();
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return generateState();
    }

    public String getCountryName() {
        return countryName;
    }

    public Conditions getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(Conditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    private String generateQuery() {
        if (query == null) {
            if (getStateName() == null) return (cityName + "," + countryName).toLowerCase();
            else return (cityName + "," + getStateName()).toLowerCase();
        } else {
            return query;
        }
    }

    private String generateState() {
        if (stateName == null || stateName.equals("")) return null;
        else return stateName;
    }

    @Override
    public String toString() {
        if (getStateName() == null) return cityName + ", " + countryName;
        else return cityName + ", " + stateName + ", " + countryName;
    }
}
