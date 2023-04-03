package org.jcd2052.pages;

import aquality.selenium.elements.interfaces.ILabel;
import org.jcd2052.pages.forms.GreetForm;
import org.jcd2052.pages.forms.SideBarForm;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainPage extends BasePage {
    private final GreetForm greetForm;
    private final SideBarForm sideBarForm;
    private final ILabel label = getElementFactory()
            .getLabel(By.xpath("//*[@id=\"view-floor\"]/div"), "Label.");

    public MainPage() {
        super(By.id("view-render"), "Main Page.");
        this.sideBarForm = new SideBarForm();
        this.greetForm = new GreetForm();
    }

    public GreetForm getGreetForm() {
        return greetForm;
    }

    public SideBarForm getSideBarForm() {
        return sideBarForm;
    }

    public BufferedImage getImageOfWorkplace() {
        try {
            return ImageIO.read(label.getElement().getScreenshotAs(OutputType.FILE));
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't get an image.\n" + e);
        }
    }
}