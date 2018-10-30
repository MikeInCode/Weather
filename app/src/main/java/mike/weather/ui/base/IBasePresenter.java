package mike.weather.ui.base;

public interface IBasePresenter<T extends IBaseView> {
    void attach(T view);
    void detach();
    void backBtnClicked();
    void aerisWeatherClicked();
}
