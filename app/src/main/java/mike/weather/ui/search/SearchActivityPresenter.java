package mike.weather.ui.search;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import mike.weather.data.IDataManager;
import mike.weather.data.model.City;
import mike.weather.data.model.ErrorStateModel;
import mike.weather.ui.base.BasePresenter;

public class SearchActivityPresenter extends BasePresenter<SearchActivityContract.View> implements SearchActivityContract.Presenter {

    @Inject
    public SearchActivityPresenter(IDataManager dataManager, CompositeDisposable disposables) {
        super(dataManager, disposables);
    }

    @Override
    public void setTextChangeObservable(Observable<CharSequence> observable) {
        getDisposables().add(observable
                .filter(query -> query.length() > 0)
                .switchMapSingle(query -> getDataManager().getCitySearchResponse(query.toString())
                        .map(response -> {
                            ErrorStateModel.setError(null);
                            return response.getData();
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
                                getView().showSuggestedCitiesList(list);
                                getView().hideCityNotFoundMessage();
                            } else {
                                getView().hideSuggestedCitiesList();
                                getView().showCityNotFoundMessage();
                            }
                            if (ErrorStateModel.isError()) {
                                getView().showErrorToast(ErrorStateModel.getErrorMessage());
                            }
                        })
        );
    }

    @Override
    public void cityClicked(City cityToAdd) {
        getDataManager().addCityToDb(cityToAdd);
        getView().goBack();
    }
}
