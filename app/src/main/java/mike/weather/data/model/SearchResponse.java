package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("response")
    private List<City> citiesList;

    public List<City> getCitiesList() {
        return citiesList;
    }
}
