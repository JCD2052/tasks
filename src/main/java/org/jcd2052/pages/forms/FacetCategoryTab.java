package org.jcd2052.pages.forms;

public enum FacetCategoryTab implements ISideBarTab {
    DINNING_ROOM("Dining room");

    private final String tabName;

    FacetCategoryTab(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public String getTabName() {
        return tabName;
    }
}
