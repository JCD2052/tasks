package org.example;

import aquality.appium.mobile.application.AqualityServices;
import org.example.screens.FileOpeningScreen;
import org.example.screens.MainScreen;
import org.example.screens.TextEditorScreen;
import org.example.screens.categories.AddFileCategory;
import org.example.screens.categories.ContextMenuCategory;
import org.example.screens.forms.ContextMenuChoiceForm;
import org.example.screens.forms.CreateFileForm;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        AqualityServices.getApplication(); //before

        MainScreen mainScreen = new MainScreen();
        mainScreen.waitUntilSpinnerGone();

        mainScreen.clickAddUploadDocument();
        mainScreen.choseFromCreateFileMenu(AddFileCategory.NEW_TEXT_DOCUMENT);

        CreateFileForm createFileForm = new CreateFileForm();
        String someRandomName = "some random name";
        createFileForm.createFile(someRandomName);
        FileOpeningScreen fileCreationScreen = new FileOpeningScreen();
        fileCreationScreen.waitForPageLoading();
        fileCreationScreen.isFilenameMatched(someRandomName);
        fileCreationScreen.waitUntilSpinnerDisappears();

        TextEditorScreen textEditorScreen = new TextEditorScreen();
        textEditorScreen.waitForPageLoading();

        textEditorScreen.enterText("sadasdasdasdas");
        textEditorScreen.closeEditor();
        mainScreen.waitUntilSpinnerGone();

        mainScreen.openContextMenu(someRandomName);
        mainScreen.choseFromFileContextMenu(ContextMenuCategory.DELETE);
        ContextMenuChoiceForm menuChoiceForm = new ContextMenuChoiceForm();
        menuChoiceForm.waitForPageLoading();
        menuChoiceForm.clickDelete();

        Thread.sleep(10000);
        AqualityServices.getApplication().quit();
    }
}
