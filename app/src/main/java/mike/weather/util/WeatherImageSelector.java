package mike.weather.util;

import mike.weather.R;

public class WeatherImageSelector {
    public static int getImageResource(String conditionId, String iconId) {
        if (conditionId.matches("2\\d{2}")) {    //2XX - Thunderstorm
            if (conditionId.equals("210") || conditionId.equals("211") || conditionId.equals("221")) {
                if (iconId.contains("d")) {
                    return R.drawable.weather_thunderstorm_d;
                } else {
                    return R.drawable.weather_thunderstorm_n;
                }
            } else if (conditionId.equals("212")) {
                return R.drawable.weather_heavy_thunderstorm;
            } else {
                return R.drawable.weather_thunderstorm_and_rain;
            }
        } else if (conditionId.matches("3\\d{2}")) {    //3XX - Drizzle
            return R.drawable.weather_clouds_and_rain;
        } else if (conditionId.matches("5\\d{2}")) {    //5XX - Rain
            if (iconId.contains("d")) {
                return R.drawable.weather_sun_and_rain_d;
            } else {
                return R.drawable.weather_sun_and_rain_n;
            }
        } else if (conditionId.matches("6\\d{2}")) {    //6XX - Snow
            if (conditionId.equals("602")) {
                return R.drawable.weather_heavy_snow;
            } else if (conditionId.equals("600") || conditionId.equals("601")) {
                if (iconId.contains("d")) {
                    return R.drawable.weather_snow_d;
                } else {
                    return R.drawable.weather_snow_n;
                }
            } else {
                return R.drawable.weather_snow_and_rain;
            }
        } else if (conditionId.matches("7\\d{2}")) {    //7XX - Atmosphere
            if (conditionId.equals("741")) {
                return R.drawable.weather_fog;
            } else if (conditionId.equals("771")) {
                return R.drawable.weather_squalls;
            } else if (conditionId.equals("781")) {
                return R.drawable.weather_tornado;
            } else {
                return R.drawable.weather_fog;
            }
        } else if (conditionId.equals("800")) {    //800 - Clear
            if (iconId.contains("d")) {
                return R.drawable.weather_clear_d;
            } else {
                return R.drawable.weather_clear_n;
            }
        } else if (conditionId.matches("80\\d")) {   //80X - Clouds
            if (conditionId.equals("801")) {
                if (iconId.contains("d")) {
                    return R.drawable.weather_few_clouds_d;
                } else {
                    return R.drawable.weather_few_clouds_n;
                }
            } else if (conditionId.equals("802")) {
                return R.drawable.weather_clouds;
            } else {
                return R.drawable.weather_broken_clouds;
            }
        }
        return 0;
    }
}
