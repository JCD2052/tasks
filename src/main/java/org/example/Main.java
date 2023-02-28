package org.example;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.example.screens.FileCreationScreen;
import org.example.screens.MainScreen;
import org.example.screens.categories.AddFileCategory;
import org.example.screens.forms.CreateFileForm;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static aquality.appium.mobile.application.AqualityServices.getElementFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AqualityServices.getApplication();

        MainScreen mainScreen = new MainScreen();
        mainScreen.waitUntilSpinnerGone();


        mainScreen.clickAddUploadDocument();

        mainScreen.getAddUploadFileForm().performAction(AddFileCategory.NEW_TEXT_DOCUMENT);

        CreateFileForm createFileForm = new CreateFileForm();
        String someRandomName = "some random name";
        createFileForm.createFile(someRandomName);
        FileCreationScreen fileCreationScreen = new FileCreationScreen();
        fileCreationScreen.isFilenameMatched(someRandomName);
        fileCreationScreen.waitUntilSpinnerDisappears();
    }
}
