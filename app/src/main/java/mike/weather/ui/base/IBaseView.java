package mike.weather.ui.base;

public interface IBaseView {
    void showErrorToast(String errorMessage);
    void showLastUpdateDate(String date);
    void goBack();
    void goToApiWebsite();
}
