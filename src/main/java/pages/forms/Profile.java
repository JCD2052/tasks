package pages.forms;

import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import pages.BasePage;

public class Profile extends BasePage {
    private static final String BASE_LOCATOR = "top_profile_link";
    private final ILink btnShowForm = getElementFactory().getLink(By.id(BASE_LOCATOR), "Show Form");
    private final ILink btnLogOut = getElementFactory().getLink(By.id("top_logout_link"), "Log Out");

    Profile() {
        super(By.id(BASE_LOCATOR), "Profile");
    }

    public void logOut() {
        btnShowForm.click();
        btnLogOut.state().waitForClickable();
        btnLogOut.click();
    }
}
