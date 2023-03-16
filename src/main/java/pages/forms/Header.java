package pages.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BasePage;

public class Header extends BasePage {
    public Header() {
        super(By.id("page_header"), "Header");
    }

    public Profile goToProfile(){
        Profile profile = new Profile();
        if (profile.state().isDisplayed()){
            return profile;
        }else {
            Assert.fail("Not LogIn");
            return null;
        }
    }
}