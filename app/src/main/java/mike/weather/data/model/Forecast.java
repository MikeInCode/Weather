package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mike.weather.util.WeatherImageUtils;

public class Forecast {
    @SerializedName("dateTimeISO")
    private String dateTime;
    @SerializedName("icon")
    private String icon;
    @SerializedName("tempC")
    private String tempCelsius;
    @SerializedName("minTempC")
    private String minTempCelsiuis;
    @SerializedName("maxTempC")
    private String maxTempCelsiuis;

    public String getTime() {
        if (dateTime != null)
            return DateTime.parse(dateTime).toString(DateTimeFormat.shortTime());
        else return "";
    }

    public String getDate() {
        if (dateTime != null)
            return DateTime.parse(dateTime).toString(DateTimeFormat.shortDate());
        else return "";
    }

    public String getDayOfWeek() {
        if (dateTime != null)
            return DateTime.parse(dateTime).dayOfWeek().getAsText();
        else return "";
    }

    public int getIcon() {
        if (icon != null)
            return WeatherImageUtils.getImageResource(icon);
        else return 0;
    }

    public String getTempCelsius() {
        if (tempCelsius != null)
            return new BigDecimal(tempCelsius).setScale(0, RoundingMode.HALF_UP) + "°";
        else return "";
    }

    public String getMinTempCelsius() {
        if (minTempCelsiuis != null)
            return new BigDecimal(minTempCelsiuis).setScale(0, RoundingMode.HALF_UP) + "°";
        else return "";
    }

    public String getMaxTempCelsius() {
        if (maxTempCelsiuis != null)
            return new BigDecimal(maxTempCelsiuis).setScale(0, RoundingMode.HALF_UP) + "°";
        else return "";
    }
}
