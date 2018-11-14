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
    private double tempCelsius;
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
        return weatherDescription;
    }

    public String getTempCelsius() {
        return new BigDecimal(tempCelsius).setScale(0, RoundingMode.HALF_UP) + "°";
    }

    public int getIcon() {
        return WeatherImageUtils.getImageResource(icon);
    }

    public String getFeelsLikeTempCelsius() {
        return new BigDecimal(feelsLikeTempCelsius).setScale(0, RoundingMode.HALF_UP) + "°";
    }

    public String getSunrise() {
        return DateTime.parse(sunrise).toString(DateTimeFormat.shortTime());
    }

    public String getSunset() {
        return DateTime.parse(sunset).toString(DateTimeFormat.shortTime());
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public String getWindSpeedKPH() {
        return windSpeedKPH + " km/h";
    }

    public String getCloudsCoverage() {
        return cloudsCoverage + "%";
    }

    public String getPressureMillibars() {
        return pressureMillibars + " mbar";
    }
}
