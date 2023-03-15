package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    public HomePage() {
        super(By.xpath("//section[contains(@class, 'connections')]"),
                "HomePage");
    }
}