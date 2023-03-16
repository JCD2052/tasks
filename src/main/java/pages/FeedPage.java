package pages;

import org.openqa.selenium.By;

public class FeedPage extends MainPage {

    public FeedPage() {
        super(By.xpath("//div[contains(@class, 'stories') and contains(@class, 'cont')]"),
                "Feed");

    }
}
