package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import mike.weather.App;
import mike.weather.R;
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
        return DateTime.parse(dateTime).toString(DateTimeFormat.shortTime());
    }

    public String getDate() {
        return DateTime.parse(dateTime).toString(DateTimeFormat.shortDate());
    }

    public String getDayOfWeek() {
        return DateTime.parse(dateTime).dayOfWeek().getAsText();
    }

    public int getIcon() {
        return WeatherImageUtils.getImageResource(icon);
    }

    public String getTempCelsius() {
        return tempCelsius + "°";
    }

    public String getMinTempCelsius() {
        return minTempCelsiuis + "°";
    }

    public String getMaxTempCelsius() {
        return maxTempCelsiuis + "°";
    }
}
