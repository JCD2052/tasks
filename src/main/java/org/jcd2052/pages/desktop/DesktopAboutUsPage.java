package org.jcd2052.pages.desktop;

import org.jcd2052.pages.AbstractAboutUsPage;

public class DesktopAboutUsPage extends AbstractAboutUsPage {
    @Override
    public String getAllTextFromInfo() {
        return getTextFromInfoWindow();
    }
}