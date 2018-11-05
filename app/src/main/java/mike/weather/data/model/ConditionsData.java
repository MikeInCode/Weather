package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class ConditionsData {

    @SerializedName("ob")
    private CurrentConditions currentConditions;

    public CurrentConditions getCurrentConditions() {
        return currentConditions;
    }
}
