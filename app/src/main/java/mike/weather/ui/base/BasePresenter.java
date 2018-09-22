package mike.weather.ui.base;

public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}
