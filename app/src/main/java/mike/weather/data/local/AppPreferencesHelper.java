package mike.weather.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private SharedPreferences sharedPreferences;
    private final String BASE_UNITS = "base_units";

    @Inject
    public AppPreferencesHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setBaseUnits(boolean newValue) {
        sharedPreferences.edit().putBoolean(BASE_UNITS, newValue).apply();
    }

    public boolean getBaseUnits() {
        return sharedPreferences.getBoolean(BASE_UNITS, false);
    }
}
