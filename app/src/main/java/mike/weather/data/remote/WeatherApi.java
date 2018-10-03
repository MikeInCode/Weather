package mike.weather.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String apiKey = "wf3PuYlZ8bDoNq8bvrcxJt888K5nKyLJ";

    @GET("locations/v1/cities/autocomplete")
    Call<List<SearchCity>> getSearchingResult(@Query("apikey") String apiKey, @Query("q") String searchingQuery);

    class SearchCity {
        @SerializedName("Key")
        private String key;
        @SerializedName("LocalizedName")
        private String name;
        @SerializedName("Country")
        private Country country;
        @SerializedName("AdministrativeArea")
        private AdministrativeArea area;

        public SearchCity(String key, String name, Country country, AdministrativeArea area) {
            this.key = key;
            this.name = name;
            this.country = country;
            this.area = area;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public AdministrativeArea getArea() {
            return area;
        }

        public void setArea(AdministrativeArea area) {
            this.area = area;
        }

        public static class Country {
            @SerializedName("LocalizedName")
            private String name;

            public Country(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class AdministrativeArea {
            @SerializedName("ID")
            private String id;

            public AdministrativeArea(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
