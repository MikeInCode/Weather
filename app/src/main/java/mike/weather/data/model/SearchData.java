package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class SearchData {

    @SerializedName("place")
    private City city;

    public City getCity() {
        return city;
    }
}
