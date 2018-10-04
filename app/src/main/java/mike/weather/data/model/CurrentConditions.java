package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class CurrentConditions {
    @SerializedName("WeatherText")
    private String weatherText;
    @SerializedName("IsDayTime")
    private boolean isDay;
    @SerializedName("Temperature")
    private Temperature temperature;

    public CurrentConditions(String weatherText, boolean isDay, Temperature temperature) {
        this.weatherText = weatherText;
        this.isDay = isDay;
        this.temperature = temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public static class Temperature {
        @SerializedName("Metric")
        private Temperature.Metric metric;
        @SerializedName("Imperial")
        private Temperature.Imperial imperial;

        public Temperature(Temperature.Metric metric, Temperature.Imperial imperial) {
            this.metric = metric;
            this.imperial = imperial;
        }

        public Temperature.Metric getMetric() {
            return metric;
        }

        public void setMetric(Temperature.Metric metric) {
            this.metric = metric;
        }

        public Temperature.Imperial getImperial() {
            return imperial;
        }

        public void setImperial(Temperature.Imperial imperial) {
            this.imperial = imperial;
        }

        public static class Metric {
            @SerializedName("Value")
            private String degrees;

            public Metric(String degrees) {
                this.degrees = degrees;
            }

            public String getDegrees() {
                return new BigDecimal(degrees).stripTrailingZeros().toPlainString() + "°";
            }

            public void setDegrees(String degrees) {
                this.degrees = degrees;
            }
        }

        public static class Imperial {
            @SerializedName("Value")
            private String degrees;

            public Imperial(String degrees) {
                this.degrees = degrees;
            }

            public String getDegrees() {
                return new BigDecimal(degrees).stripTrailingZeros().toPlainString() + "°";
            }

            public void setDegrees(String degrees) {
                this.degrees = degrees;
            }
        }
    }
}
