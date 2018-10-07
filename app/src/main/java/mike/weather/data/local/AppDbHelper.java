package mike.weather.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.model.City;

@Singleton
public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    private static final String TABLE_CITIES = "cities";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_API_ID = "api_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COUNTRY_CODE = "country_code";


    @Inject
    public AppDbHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_CITIES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_API_ID + " INTEGER UNIQUE, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_COUNTRY_CODE + " TEXT)";

        String initialDataQuery = "INSERT INTO " + TABLE_CITIES + " ("
                + COLUMN_API_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_COUNTRY_CODE + ") VALUES (2643743, 'London', 'GB')";

        db.execSQL(createQuery);
        db.execSQL(initialDataQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
        onCreate(db);
    }

    @Override
    public boolean insertCity(City city) {
        if (getFieldsCount(TABLE_CITIES) < 20) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_API_ID, city.getId());
            values.put(COLUMN_NAME, city.getName());
            values.put(COLUMN_COUNTRY_CODE, city.getCountryCode());
            db.insert(TABLE_CITIES, null, values);
            db.close();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<City> readAllCities() {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {
                COLUMN_API_ID,
                COLUMN_NAME,
                COLUMN_COUNTRY_CODE
        };

        Cursor cursor = db.query(
                TABLE_CITIES,
                projection,
                null,
                null,
                null,
                null,
                null);

        List<City> citiesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            citiesList.add(new City(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_API_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    new City.CountryInfo(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY_CODE)))));
        }
        cursor.close();
        return citiesList;
    }

    private long getFieldsCount(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, tableName);
        db.close();
        return count;
    }
}
