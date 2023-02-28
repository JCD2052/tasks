package org.example.screens.categories;

public enum AddFileCategory implements Category {
    NEW_TEXT_DOCUMENT("Create new текстовый документ", "New text document"); //text locator
    private final String locatorValue;
    private final String elementName;

    AddFileCategory(String locatorValue, String elementName) {
        this.locatorValue = locatorValue;
        this.elementName = elementName;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public String getElementName() {
        return elementName;
    }
}
