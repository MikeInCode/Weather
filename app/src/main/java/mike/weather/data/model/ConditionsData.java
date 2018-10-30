package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class ConditionsData {

    @SerializedName("ob")
    private Conditions conditions;

    public Conditions getConditions() {
        return conditions;
    }
}
