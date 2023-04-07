package org.jcd2052.utiils;

import org.jcd2052.pages.AbstractAboutUsPage;
import org.jcd2052.pages.AbstractHomePage;
import org.jcd2052.pages.desktop.DesktopAboutUsPage;
import org.jcd2052.pages.desktop.DesktopHomePage;
import org.jcd2052.pages.mobile.MobileAboutUsPage;
import org.jcd2052.pages.mobile.MobileHomePage;
import org.openqa.selenium.Dimension;

public class PageFactory {
    private static final int MOBILE_SIZE_WIDTH_BOUND = 1180;

    private PageFactory() {

    }

    public static AbstractHomePage getHomePage() {
        if (isMobile()) {
            return new MobileHomePage();
        } else {
            return new DesktopHomePage();
        }
    }

    public static AbstractAboutUsPage getAboutUsPage() {
        if (isMobile()) {
            return new MobileAboutUsPage();
        } else {
            return new DesktopAboutUsPage();
        }
    }

    private static boolean isMobile() {
        Dimension screenSize = BrowserUtils.getBrowserSize();
        return screenSize.getWidth() < MOBILE_SIZE_WIDTH_BOUND;
    }
}
