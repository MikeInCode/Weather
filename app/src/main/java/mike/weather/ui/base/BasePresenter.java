package mike.weather.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import mike.weather.data.DataManager;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    private T view;
    private DataManager dataManager;
    private CompositeDisposable disposables;

    public BasePresenter(DataManager dataManager, CompositeDisposable disposables) {
        this.dataManager = dataManager;
        this.disposables = disposables;
    }

    @Override
    public void attach(T view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
        disposables.clear();
    }

    @Override
    public void backBtnClicked() {
        view.goBack();
    }

    @Override
    public void aerisWeatherClicked() {
        view.goToApiWebsite();
    }

    public T getView() {
        return view;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getDisposables() {
        return disposables;
    }
}
