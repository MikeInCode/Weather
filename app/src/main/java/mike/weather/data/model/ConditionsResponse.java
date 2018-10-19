package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class ConditionsResponse {
    @SerializedName("response")
    private Data data;

    public String getTemp() {
        return data.conditionsValues.tempCelsius;
    }

    public String getIcon() {
        return data.conditionsValues.icon;
    }

    private static class Data {
        @SerializedName("ob")
        private ConditionsValues conditionsValues;

        private static class ConditionsValues {
            @SerializedName("tempC")
            private String tempCelsius;
            @SerializedName("icon")
            private String icon;
        }
    }
}
