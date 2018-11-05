package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastData {
    @SerializedName("periods")
    private List<Forecast> forecastList;

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
