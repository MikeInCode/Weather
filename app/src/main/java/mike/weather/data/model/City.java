package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mike.weather.util.WeatherImageUtils;

public class City {
    @SerializedName("place")
    private Place place;
    private String temp;
    private int icon;

    public City(Place place) {
        this.place = place;
    }

    public String getQuery() {
        if (getStateName() == null) return (getCityName() + "," + getCountryName()).toLowerCase();
        else return (getCityName() + "," + getStateName()).toLowerCase();
    }

    public String getCityName() {
        return place.cityName;
    }

    public String getStateName() {
        if (place.stateName == null || place.stateName.equals("")) return null;
        else return place.stateName;
    }

    public String getCountryName() {
        return place.countryName;
    }

    public String getTemp() {
        if (temp == null) return "--";
        else return temp;
    }

    public void setTemp(String temp) {
        this.temp = new BigDecimal(temp).setScale(0, RoundingMode.HALF_UP) + "°";
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = WeatherImageUtils.getImageResource(icon);
    }

    @Override
    public String toString() {
        if (getStateName() == null) return getCityName() + ", " + getCountryName();
        else return getCityName() + ", " + getStateName() + ", " + getCountryName();
    }

    public static class Place {
        @SerializedName("name")
        private String cityName;
        @SerializedName("state")
        private String stateName;
        @SerializedName("countryFull")
        private String countryName;

        public Place(String cityName, String stateName, String countryName) {
            this.cityName = cityName;
            this.stateName = stateName;
            this.countryName = countryName;
        }
    }
}
