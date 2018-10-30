package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastData {
    @SerializedName("periods")
    private List<Conditions> forecastList;

    public List<Conditions> getForecastList() {
        return forecastList;
    }
}
