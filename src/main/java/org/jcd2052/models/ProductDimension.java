package org.jcd2052.models;

public record ProductDimension(double height, double width, double length) {
    public boolean valuesAreNotNull() {
        return height != 0 && width != 0 && length != 0;
    }
}
