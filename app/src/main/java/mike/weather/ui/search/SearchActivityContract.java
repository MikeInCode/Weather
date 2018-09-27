package mike.weather.ui.search;

import mike.weather.ui.base.BasePresenter;

public interface SearchActivityContract {
    interface View {
        void goBack();
    }

    interface Presenter extends BasePresenter<View> {
        void backBtClicked();
        SearchActivityContract.View get();
    }
}
