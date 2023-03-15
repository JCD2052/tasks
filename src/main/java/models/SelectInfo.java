package models;

import java.util.Objects;

public class SelectInfo {
    private final String maker;
    private final String model;
    private final String year;

    public SelectInfo(String maker, String model, String year) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectInfo that = (SelectInfo) o;
        return Objects.equals(maker, that.maker) && Objects.equals(model, that.model) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maker, model, year);
    }

    @Override
    public String toString() {
        return "SelectInfo{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
