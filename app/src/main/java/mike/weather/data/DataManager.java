package mike.weather.data;

import javax.inject.Inject;

import mike.weather.data.local.IDbHelper;

public class DataManager implements IDataManager {

    private IDbHelper dbHelper;

    @Inject
    public DataManager(IDbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void loadDataFromDb() {

    }
}
