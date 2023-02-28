package org.example.screens.categories;

public enum ContextMenuCategory implements Category {
    DELETE("Delete", "Delete file");

    private final String locatorValue;
    private final String elementName;

    ContextMenuCategory(String locatorValue, String elementName) {
        this.locatorValue = locatorValue;
        this.elementName = elementName;
    }

    @Override
    public String getLocatorValue() {
        return locatorValue;
    }

    @Override
    public String getElementName() {
        return elementName;
    }
}
