package models;

public class SearchInfo {
    private final String maker;
    private final String model;
    private final String newUsed;
    private final String zipCode;
    private final String distance;
    private final String price;

    public SearchInfo(String maker, String model, String newUsed, String zipCode,
                      String distance, String price) {
        this.maker = maker;
        this.model = model;
        this.newUsed = newUsed;
        this.zipCode = zipCode;
        this.distance = distance;
        this.price = price;
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

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }
}
