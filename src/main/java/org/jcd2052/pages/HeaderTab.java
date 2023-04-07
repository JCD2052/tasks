package org.jcd2052.pages;

public enum HeaderTab {
    ABOUT_US("ABOUT US");

    private final String tabName;

    HeaderTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}
