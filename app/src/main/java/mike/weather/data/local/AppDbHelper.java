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

import io.reactivex.Observable;
import mike.weather.data.model.City;

@Singleton
public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    private static final String TABLE_CITIES = "cities";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_API_QUERY = "api_query";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_STATE = "state";
    private static final String COLUMN_COUNTRY = "country";

    @Inject
    public AppDbHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_CITIES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_API_QUERY + " TEXT UNIQUE, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_STATE + " TEXT, "
                + COLUMN_COUNTRY + " TEXT)";

        String initialDataQuery = "INSERT INTO " + TABLE_CITIES + " ("
                + COLUMN_API_QUERY + ", "
                + COLUMN_NAME + ", "
                + COLUMN_STATE + ", "
                + COLUMN_COUNTRY + ") VALUES ('london,united kingdom', 'London', null, 'United Kingdom')";

        db.execSQL(createQuery);
        db.execSQL(initialDataQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
        onCreate(db);
    }

    @Override
    public void insertCity(City city) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_API_QUERY, city.getQuery());
        values.put(COLUMN_NAME, city.getCityName());
        values.put(COLUMN_STATE, city.getStateName());
        values.put(COLUMN_COUNTRY, city.getCountryName());
        db.insert(TABLE_CITIES, null, values);
        db.close();
    }

    @Override
    public Observable<City> readAllCities() {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {
                COLUMN_API_QUERY,
                COLUMN_NAME,
                COLUMN_STATE,
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

        List<City> citiesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            citiesList.add(new City(new City.Place(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY)))));
        }
        cursor.close();
        return Observable.fromIterable(citiesList);
    }
}
