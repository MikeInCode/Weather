package mike.weather.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.model.MainCity;
import mike.weather.data.model.SearchCity;

@Singleton
public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    private static final String TABLE_CITIES = "cities";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_API_KEY = "api_key";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COUNTRY = "country";


    @Inject
    public AppDbHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_CITIES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_API_KEY + " INTEGER UNIQUE, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_COUNTRY + " TEXT)";

        String initialDataQuery = "INSERT INTO " + TABLE_CITIES + " ("
                + COLUMN_API_KEY + ", "
                + COLUMN_NAME + ", "
                + COLUMN_COUNTRY + ") VALUES (328328, 'London', 'United Kingdom')";

        db.execSQL(createQuery);
        db.execSQL(initialDataQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
        onCreate(db);
    }

    @Override
    public void insertCity(SearchCity searchCity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_API_KEY, searchCity.getKey());
        values.put(COLUMN_NAME, searchCity.getName());
        values.put(COLUMN_COUNTRY, searchCity.getCountry().getName());
        db.insert(TABLE_CITIES, null, values);
        db.close();
    }

    @Override
    public List<MainCity> readAllCities() {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {
                COLUMN_API_KEY,
                COLUMN_NAME,
                COLUMN_COUNTRY
        };

        Cursor cursor = db.query(
                TABLE_CITIES,
                projection,
                null,
                null,
                null,
                null,
                null);

        List<MainCity> citiesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            citiesList.add(new MainCity(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_API_KEY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY))));
        }
        cursor.close();
        return citiesList;
    }
}