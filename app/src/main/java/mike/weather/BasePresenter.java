package mike.weather;

public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}