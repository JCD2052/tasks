package pages;

import org.openqa.selenium.By;

public class LicensePage extends MainPage {
    public LicensePage() {
        super(By.id("activationCode"), "License");
    }
}