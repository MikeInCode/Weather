package mike.weather.ui.search;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import mike.weather.data.DataManager;
import mike.weather.data.model.City;
import mike.weather.data.model.ErrorStateModel;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View view;
    private DataManager dataManager;
    private CompositeDisposable disposables;

    @Inject
    public SearchActivityPresenter(DataManager dataManager, CompositeDisposable disposables) {
        this.dataManager = dataManager;
        this.disposables = disposables;
    }

    @Override
    public void attach(SearchActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
        disposables.clear();
    }

    @Override
    public void setTextChangeObservable(Observable<CharSequence> observable) {
        disposables.add(observable
                .filter(query -> query.length() > 0)
                .switchMapSingle(query -> dataManager.getCitySearchResponse(query.toString())
                        .map(response -> {
                            ErrorStateModel.setError(null);
                            return response.getCitiesList();
                        })
                        .onErrorReturn(throwable -> {
                            ErrorStateModel.setError(throwable);
                            return new ArrayList<>();
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                )
                .subscribe(
                        list -> {
                            if (!list.isEmpty()) {
                                view.showSuggestedCitiesList(list);
                                view.hideCityNotFoundMessage();
                            } else {
                                view.hideSuggestedCitiesList();
                                view.showCityNotFoundMessage();
                            }
                            if (ErrorStateModel.isError()) {
                                view.showErrorToast(ErrorStateModel.getErrorMessage());
                            }
                        })
        );
    }

    @Override
    public void setBackBtnObservable(Observable<Object> observable) {
        disposables.add(observable
                .subscribe(a -> view.goBack()));
    }

    @Override
    public void cityClicked(City cityToAdd) {
        dataManager.addCityToDb(cityToAdd);
        view.goBack();
    }
}
