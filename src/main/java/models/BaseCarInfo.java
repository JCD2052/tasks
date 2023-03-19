package models;

public class BaseCarInfo {
    private final String maker;
    private final String model;
    private final String year;

    public BaseCarInfo(String maker, String model, String year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }
}
