package mike.weather.data.model;

import java.io.IOException;

public class ErrorStateModel {
    private static String errorMessage;

    public static void setError(Throwable throwable) {
        if (throwable == null) {
            errorMessage = null;
        } else if (throwable instanceof IOException) {
            errorMessage = "Network error!";
        } else {
            errorMessage = "Server error!";
        }
    }

    public static boolean isError() {
        return errorMessage != null;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }
}
