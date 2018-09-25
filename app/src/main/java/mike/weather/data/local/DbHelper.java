package mike.weather.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;

public class DbHelper extends SQLiteOpenHelper implements IDbHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "main_db";
    private static final String TABLE_CITIES = "cities";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CITY_NAME = "name";
    private static final String COLUMN_CITY_API_ID = "api_id";

    @Inject
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE "
                + TABLE_CITIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CITY_NAME + " TEXT,"
                + COLUMN_CITY_API_ID + " INTEGER" + ")";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
        onCreate(db);
    }
}
