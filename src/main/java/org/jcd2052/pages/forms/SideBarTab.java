package org.jcd2052.pages.forms;

public enum SideBarTab implements ISideBarTab {
    FURNISH("Furnish your room");

    private final String name;

    SideBarTab(String name) {
        this.name = name;
    }

    @Override
    public String getTabName() {
        return name;
    }
}
