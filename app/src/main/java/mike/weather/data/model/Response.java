package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("list")
    private List<City> citiesList;

    public List<City> getCitiesList() {
        return citiesList;
    }
}
