package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mike.weather.util.WeatherImageUtils;

public class CurrentConditions {
    @SerializedName("weatherPrimary")
    private String weatherDescription;
    @SerializedName("tempC")
    private String tempCelsius;
    @SerializedName("icon")
    private String icon;
    @SerializedName("feelslikeC")
    private String feelsLikeTempCelsius;
    @SerializedName("sunriseISO")
    private String sunrise;
    @SerializedName("sunsetISO")
    private String sunset;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("windSpeedKPH")
    private String windSpeedKPH;
    @SerializedName("sky")
    private String cloudsCoverage;
    @SerializedName("pressureMB")
    private String pressureMillibars;

    public String getWeatherDescription() {
        if (weatherDescription != null) return weatherDescription;
        else return "";
    }

    public String getTempCelsius() {
        if (tempCelsius != null)
            return new BigDecimal(tempCelsius).setScale(0, RoundingMode.HALF_UP) + "°";
        else return "";
    }

    public int getIcon() {
        if (icon != null) return WeatherImageUtils.getImageResource(icon);
        else return 0;
    }

    public String getFeelsLikeTempCelsius() {
        if (feelsLikeTempCelsius != null)
            return new BigDecimal(feelsLikeTempCelsius).setScale(0, RoundingMode.HALF_UP) + "°";
        else return "";
    }

    public String getSunrise() {
        if (sunrise != null)
            return DateTime.parse(sunrise).toString(DateTimeFormat.shortTime());
        else return "";
    }

    public String getSunset() {
        if (sunset != null)
            return DateTime.parse(sunset).toString(DateTimeFormat.shortTime());
        else return "";
    }

    public String getHumidity() {
        if (humidity != null)
            return humidity + "%";
        else return "";
    }

    public String getWindSpeedKPH() {
        if (windSpeedKPH != null)
            return windSpeedKPH + " km/h";
        else return "";
    }

    public String getCloudsCoverage() {
        if (cloudsCoverage != null)
            return cloudsCoverage + "%";
        else return "";
    }

    public String getPressureMillibars() {
        if (pressureMillibars != null)
            return pressureMillibars + " mbar";
        else return "";
    }
}
