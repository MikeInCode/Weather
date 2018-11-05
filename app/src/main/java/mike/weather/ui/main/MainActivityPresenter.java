package mike.weather.ui.main;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mike.weather.data.DataManager;
import mike.weather.data.model.City;
import mike.weather.data.model.ErrorStateModel;
import mike.weather.ui.base.BasePresenter;

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View> implements MainActivityContract.Presenter {
    private Disposable cityListDisposable;

    @Inject
    public MainActivityPresenter(DataManager dataManager, CompositeDisposable disposables) {
        super(dataManager, disposables);
    }

    @Override
    public void setCitiesList() {
        if (cityListDisposable != null) {
            getDisposables().remove(cityListDisposable);
        }
        cityListDisposable = getCitiesListObservable()
                .subscribe(
                        list -> {
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showCitiesList(list);
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                );
        getDisposables().add(cityListDisposable);
    }

    private Single<List<City>> getCitiesListObservable() {
        return getDataManager().getCitiesFromDb()
                .flatMapSingle(city ->
                        getDataManager().getCityConditionsResponse(city.getQuery())
                                .map(response -> {
                                    ErrorStateModel.setError(null);
                                    city.setCurrentConditions(response.getData().getCurrentConditions());
                                    return city;
                                })
                                .onErrorReturn(throwable -> {
                                    ErrorStateModel.setError(throwable);
                                    return city;
                                })
                )
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> getView().hideRefreshingStatus());
    }

    @Override
    public void setRefreshObservable(Observable<Object> observable) {
        getDisposables().add(observable
                .flatMapSingle(o -> getCitiesListObservable())
                .subscribe(
                        list -> {
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showCitiesList(list);
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                )
        );
    }

    @Override
    public void itemSwipedToDelete(int position, City cityToDelete) {
        getView().deleteCityFromList(position);
        getDataManager().deleteCityFromDb(cityToDelete);
    }

    @Override
    public void cityClicked(City city) {
        getView().goToDetailedInfo(city);
    }

    @Override
    public void addCityBtnClicked() {
        getView().goToSearch();
    }
}
