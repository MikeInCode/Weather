package mike.weather.data.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {

    @SerializedName("response")
    private T data;

    public T getData() {
        return data;
    }
}
