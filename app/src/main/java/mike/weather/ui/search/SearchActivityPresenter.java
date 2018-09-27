package mike.weather.ui.search;

import javax.inject.Inject;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {
    private SearchActivityContract.View view;

    @Inject
    public SearchActivityPresenter() {

    }

    @Override
    public void attach(SearchActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void backBtClicked() {
        view.goBack();
    }

    @Override
    public SearchActivityContract.View get() {
        return view;
    }
}
