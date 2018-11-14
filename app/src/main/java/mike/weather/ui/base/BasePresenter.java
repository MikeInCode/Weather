package mike.weather.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import mike.weather.data.IDataManager;

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    private T view;
    private IDataManager dataManager;
    private CompositeDisposable disposables;

    public BasePresenter(IDataManager dataManager, CompositeDisposable disposables) {
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

    public IDataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getDisposables() {
        return disposables;
    }
}
