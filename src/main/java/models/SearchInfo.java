package models;

public class SearchInfo {
    private final CarInfo carInfo;
    private final String newUsed;
    private final String zipCode;
    private final String distance;
    private final String price;

    public SearchInfo(CarInfo carInfo, String newUsed, String zipCode,
                      String distance, String price) {
        this.carInfo = carInfo;
        this.newUsed = newUsed;
        this.zipCode = zipCode;
        this.distance = distance;
        this.price = price;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDistance() {
        return distance;
    }

    public String getPrice() {
        return price;
    }

    public String getNewUsed() {
        return newUsed;
    }
}
