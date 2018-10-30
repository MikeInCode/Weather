package mike.weather.ui.detailed;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import mike.weather.data.DataManager;
import mike.weather.data.model.City;
import mike.weather.data.model.Conditions;
import mike.weather.data.model.ErrorStateModel;
import mike.weather.ui.base.BasePresenter;

public class DetailedActivityPresenter extends BasePresenter<DetailedActivityContract.View> implements DetailedActivityContract.Presenter {
    private City mainCity;

    @Inject
    public DetailedActivityPresenter(DataManager dataManager, CompositeDisposable disposables) {
        super(dataManager, disposables);
    }

    @Override
    public void initCity(City mainCity) {
        this.mainCity = mainCity;
    }

    @Override
    public void setCityData() {
        getDisposables().add(getCityInfoObservable()
                .subscribe(
                        city -> {
                            getView().showCityInfo(city);
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                ));
    }

    @Override
    public void setForecastData() {
        getDisposables().add(getForecastListObservable()
                .subscribe(
                        list -> {
                            getView().showForecast(list);
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                ));
    }

    private Single<City> getCityInfoObservable() {
        return Single.just(mainCity)
                .flatMap(city ->
                        getDataManager().getCityConditionsResponse(city.getQuery())
                                .map(response -> {
                                    ErrorStateModel.setError(null);
                                    city.setCurrentConditions(response.getData().getConditions());
                                    return city;
                                })
                                .onErrorReturn(throwable -> {
                                    ErrorStateModel.setError(throwable);
                                    return city;

                                })
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<Conditions>> getForecastListObservable() {
        return Single.just(mainCity)
                .flatMap(city ->
                        getDataManager().getCityForecastResponse(city.getQuery(), "1hr", "24")
                                .map(response -> {
                                    ErrorStateModel.setError(null);
                                    return response.getData().get(0).getForecastList();
                                })
                                .onErrorReturn(throwable -> {
                                    ErrorStateModel.setError(throwable);
                                    return new ArrayList<>();
                                })
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setRefreshObservable(Observable<Object> observable) {
        getDisposables().add(observable
                .flatMapSingle(o -> getCityInfoObservable())
                .subscribe(
                        city -> {
                            getView().showCityInfo(city);
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                ));
    }
}
