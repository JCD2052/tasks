package pages;

import org.openqa.selenium.By;
import pages.forms.Wall;

public class MyProfile extends MainPage {
    private final Wall wallForm;

    public MyProfile() {
        super(By.xpath("//img[contains(@class, 'page') and contains(@class, 'avatar')]"),
                "My Profile");
        this.wallForm = new Wall();
    }

    public Wall goToWall() {
        return wallForm;
    }
}