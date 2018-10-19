package mike.weather.util;

import mike.weather.R;

public class WeatherImageUtils {
    public static int getImageResource(String icon) {
        switch (icon) {
            case "clear.png":
            case "fair.png":
            case "hazy.png":
            case "sunny.png":
            case "sunnyw.png":
                return R.drawable.weather_clear;
            case "clearn.png":
            case "fairn.png":
            case "hazyn.png":
            case "sunnyn.png":
            case "sunnywn.png":
                return R.drawable.weather_clear_n;
            case "mcloudy.png":
            case "mcloudysf.png":
            case "pcloudy.png":
            case "pcloudysf.png":
                return R.drawable.weather_sun_and_clouds;
            case "mcloudyn.png":
            case "mcloudysfn.png":
            case "pcloudyn.png":
            case "pcloudysfn.png":
                return R.drawable.weather_sun_and_clouds_n;
            case "mcloudysfw.png":
            case "mcloudyw.png":
            case "pcloudyw.png":
            case "pcloudysfw.png":
                return R.drawable.weather_sun_clouds_and_wind;
            case "mcloudysfwn.png":
            case "mcloudywn.png":
            case "pcloudywn.png":
            case "pcloudysfwn.png":
                return R.drawable.weather_sun_clouds_and_wind_n;
            case "mcloudys.png":
            case "mcloudysw.png":
            case "pcloudys.png":
            case "pcloudysw.png":
                return R.drawable.weather_sun_clouds_and_snow;
            case "mcloudysn.png":
            case "mcloudyswn.png":
            case "pcloudysn.png":
            case "pcloudyswn.png":
                return R.drawable.weather_sun_clouds_and_snow_n;
            case "mcloudyt.png":
            case "mcloudytw.png":
            case "pcloudyt.png":
            case "pcloudytw.png":
                return R.drawable.weather_thunderstorm;
            case "mcloudytn.png":
            case "mcloudytwn.png":
            case "pcloudytn.png":
            case "pcloudytwn.png":
                return R.drawable.weather_thunderstorm_n;
            case "mcloudyr.png":
            case "mcloudyrw.png":
            case "pcloudyr.png":
            case "pcloudyrw.png":
                return R.drawable.weather_sun_and_rain;
            case "mcloudyrn.png":
            case "mcloudyrwn.png":
            case "pcloudyrn.png":
            case "pcloudyrwn.png":
                return R.drawable.weather_sun_and_rain_n;
            case "drizzle.png":
            case "drizzlen.png":
            case "fdrizzle.png":
            case "fdrizzlen.png":
            case "freezingrain.png":
            case "freezingrainn.png":
            case "rainw.png":
            case "rainwn.png":
            case "rain.png":
            case "rainn.png":
            case "showers.png":
            case "showersn.png":
                return R.drawable.weather_clouds_and_rain;
            case "cloudy.png":
            case "cloudyn.png":
            case "flurries.png":
            case "flurriesn.png":
                return R.drawable.weather_clouds;
            case "cloudyw.png":
            case "cloudywn.png":
            case "flurriesw.png":
            case "flurrieswn.png":
            case "wind.png":
            case "windn.png":
                return R.drawable.weather_clouds_and_wind;
            case "blizzard.png":
            case "blizzardn.png":
            case "blowingsnow.png":
            case "blowingsnown.png":
            case "snow.png":
            case "snown.png":
            case "snowshowers.png":
            case "snowshowersn.png":
                return R.drawable.weather_clouds_and_snow;
            case "rainandsnow.png":
            case "rainandsnown.png":
            case "raintosnow.png":
            case "raintosnown.png":
            case "sleet.png":
            case "sleetn.png":
            case "sleetsnow.png":
            case "sleetsnown.png":
            case "snowtorain.png":
            case "snowtorainn.png":
            case "wintrymix.png":
            case "wintrymixn.png":
                return R.drawable.weather_snow_and_rain;
            case "tstorm.png":
            case "tstormn.png":
            case "tstorms.png":
            case "tstormsn.png":
                return R.drawable.weather_thunderstorm_and_rain;
            case "cold.png":
            case "coldn.png":
                return R.drawable.weather_cold;
            case "dust.png":
            case "dustn.png":
                return R.drawable.weather_dust;
            case "fog.png":
            case "fogn.png":
            case "smoke.png":
            case "smoken.png":
                return R.drawable.weather_fog;
            case "hot.png":
                return R.drawable.weather_hot;
            default:
                return 0;
        }
    }
}
