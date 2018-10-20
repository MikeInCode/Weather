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

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private DataManager dataManager;
    private CompositeDisposable disposables;
    private Disposable cityListDisposable;

    @Inject
    public MainActivityPresenter(DataManager dataManager, CompositeDisposable disposables) {
        this.dataManager = dataManager;
        this.disposables = disposables;
    }

    @Override
    public void attach(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
        disposables.clear();
    }

    @Override
    public void onPause() {
        if (cityListDisposable != null) {
            disposables.remove(cityListDisposable);
        }
    }

    @Override
    public void setCitiesList() {
        cityListDisposable = getCitiesListObservable()
                .subscribe(
                        list -> {
                            if (ErrorStateModel.isError()) {
                                view.showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                view.showCitiesList(list);
                                view.showDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                );
        disposables.add(cityListDisposable);
    }

    private Single<List<City>> getCitiesListObservable() {
        return dataManager.getCitiesFromDb()
                .flatMapSingle(city ->
                        dataManager.getCityConditionsResponse(city.getQuery())
                                .map(response -> {
                                    ErrorStateModel.setError(null);
                                    city.setTemp(response.getTemp());
                                    city.setIcon(response.getIcon());
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
                .doAfterTerminate(() -> view.hideRefreshingStatus());
    }

    @Override
    public void setRefreshObservable(Observable<Object> observable) {
        disposables.add(observable
                .flatMapSingle(o -> getCitiesListObservable())
                .subscribe(
                        list -> {
                            if (ErrorStateModel.isError()) {
                                view.showErrorToast(ErrorStateModel.getErrorMessage());
                            } else {
                                view.showCitiesList(list);
                                view.showDate(DateTime.now().toString(DateTimeFormat.shortTime()));
                            }
                        }
                )
        );
    }

    @Override
    public void setAddBtnObservable(Observable<Object> observable) {
        disposables.add(observable
                .subscribe(a -> view.goToSearch()));
    }

    @Override
    public void setAerisWeatherObservable(Observable<Object> observable) {
        disposables.add(observable
                .subscribe(a -> view.goToApiWebsite()));
    }

    @Override
    public void itemSwipedToDelete(int position, City cityToDelete) {
        view.deleteCityFromList(position);
        dataManager.deleteCityFromDb(cityToDelete);
    }
}
