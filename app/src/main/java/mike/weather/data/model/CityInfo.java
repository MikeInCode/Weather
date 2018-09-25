package mike.weather.data.model;

public class CityInfo {
    private String mCityName;
    private String mCountryName;
    private String mTemperature;
    private int mConditionImage;

    public CityInfo(String mCityName, String mCountryName, String mTemperature, int mConditionImage) {
        this.mCityName = mCityName;
        this.mCountryName = mCountryName;
        this.mTemperature = mTemperature;
        this.mConditionImage = mConditionImage;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmCountryName() {
        return mCountryName;
    }

    public void setmCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getmConditionImage() {
        return mConditionImage;
    }

    public void setmConditionImage(int mConditionImage) {
        this.mConditionImage = mConditionImage;
    }
}
