package mike.weather.ui.detailed;

import com.jakewharton.rxbinding2.InitialValueObservable;

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
import mike.weather.data.IDataManager;
import mike.weather.data.model.City;
import mike.weather.data.model.CurrentConditions;
import mike.weather.data.model.ErrorStateModel;
import mike.weather.data.model.Forecast;
import mike.weather.ui.base.BasePresenter;

public class DetailedActivityPresenter extends BasePresenter<DetailedActivityContract.View> implements DetailedActivityContract.Presenter {
    private City mainCity;
    private String forecastTimeInterval;
    private String forecastLength;

    @Inject
    public DetailedActivityPresenter(IDataManager dataManager, CompositeDisposable disposables) {
        super(dataManager, disposables);
    }

    @Override
    public void initCity(City mainCity) {
        this.mainCity = mainCity;
    }

    private void initForecastInterval(int spinnerPosition) {
        switch (spinnerPosition) {
            case 0:
                forecastTimeInterval = "1hr";
                forecastLength = "24";
                break;
            case 1:
                forecastTimeInterval = "day";
                forecastLength = "7";
                break;
        }
    }

    private Single<City> getAllDataObservable() {
        return Single.just(mainCity)
                .flatMap(city -> getCityConditionsObservable(city)
                        .map(conditions -> {
                            city.setCurrentConditions(conditions);
                            return city;
                        }))
                .flatMap(city -> getCityForecastListObservable(city)
                        .map(forecast -> {
                            city.setForecastList(forecast);
                            return city;
                        }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<CurrentConditions> getCityConditionsObservable(City city) {
        return getDataManager().getCityConditionsResponse(city.getQuery())
                .map(response -> {
                    ErrorStateModel.setError(null);
                    return response.getData().getCurrentConditions();
                })
                .onErrorReturn(throwable -> {
                    ErrorStateModel.setError(throwable);
                    return new CurrentConditions();
                });
    }

    private Single<List<Forecast>> getCityForecastListObservable(City city) {
        return getDataManager().getCityForecastResponse(city.getQuery(), forecastTimeInterval, forecastLength)
                .map(response -> {
                    ErrorStateModel.setError(null);
                    return response.getData().get(0).getForecastList();
                })
                .onErrorReturn(throwable -> {
                    ErrorStateModel.setError(throwable);
                    return new ArrayList<>();
                });
    }

    @Override
    public void setSpinnerObservable(InitialValueObservable<Integer> observable) {
        getDisposables().add(observable
                .skipInitialValue()
                .flatMapSingle(o -> {
                    initForecastInterval(o);
                    return getAllDataObservable();
                })
                .subscribe(
                        city -> {
                            getView().showCityData(city);
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                ));
    }

    @Override
    public void setRefreshObservable(Observable<Object> observable) {
        getDisposables().add(observable
                .flatMapSingle(o -> getAllDataObservable())
                .subscribe(
                        city -> {
                            getView().showCityData(city);
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                getView().showLastUpdateDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                ));
    }
}
