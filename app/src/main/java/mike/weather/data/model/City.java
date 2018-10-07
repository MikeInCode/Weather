package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import mike.weather.util.WeatherImageSelector;

public class City {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("sys")
    private CountryInfo countryInfo;
    @SerializedName("main")
    private MainValues mainValues;
    @SerializedName("weather")
    private List<WeatherCondition> weatherCondition;

    public City(String id, String name, CountryInfo countryInfo) {
        this.id = id;
        this.name = name;
        this.countryInfo = countryInfo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryInfo.getCountryCode();
    }

    public String getCountryName() {
        return countryInfo.getCountryName();
    }

    @Override
    public String toString() {
        return name + ", " + getCountryCode() + ", " + getCountryName();
    }

    public String getCurrentTemp() {
        if (mainValues == null) {
            return "--";
        } else {
            return mainValues.getCurrentTemp();
        }
    }

    public int getConditionImage() {
        if (weatherCondition == null) {
            return 0;
        } else {
            return weatherCondition.get(0).getWeatherIcon();
        }
    }

    public static class CountryInfo {
        @SerializedName("country")
        private String countryCode;

        public CountryInfo(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getCountryName() {
            return new Locale("", countryCode).getDisplayName();
        }
    }

    public static class MainValues {
        @SerializedName("temp")
        private String currentTemp;

        public MainValues(String currentTemp) {
            this.currentTemp = currentTemp;
        }

        public String getCurrentTemp() {
            return new BigDecimal(currentTemp).setScale(0, RoundingMode.HALF_UP) + "°";
        }
    }

    public static class WeatherCondition {
        @SerializedName("id")
        private String weatherConditionId;
        @SerializedName("icon")
        private String weatherIconId;

        public int getWeatherIcon() {
            return WeatherImageSelector.getImageResource(weatherConditionId, weatherIconId);
        }
    }
}
