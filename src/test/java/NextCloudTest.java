import org.example.config.PropertyConfig;
import org.example.config.TestDataConfig;
import org.example.screens.FileOpeningScreen;
import org.example.screens.MainScreen;
import org.example.screens.SearchScreen;
import org.example.screens.TextEditorScreen;
import org.example.screens.categories.AddFileCategory;
import org.example.screens.categories.ContextMenuCategory;
import org.example.screens.forms.ContextMenuChoiceForm;
import org.example.screens.forms.CreateFileForm;
import org.example.utils.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NextCloudTest extends BaseTest {
    private final MainScreen mainScreen = new MainScreen();
    private final CreateFileForm createFileForm = new CreateFileForm();
    private final FileOpeningScreen fileOpeningScreen = new FileOpeningScreen();
    private final TextEditorScreen textEditorScreen = new TextEditorScreen();
    private final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    @Test
    public void createAndDeleteFileTest() {
        String randomFilename = RandomUtils.getRandomString(testData.getRandomLength());
        String randomText = RandomUtils.getRandomString(testData.getRandomLength());
        createFileAndDelete(randomFilename, randomText);
    }

    @Test
    public void createFileDeleteAndReloadTest() {
        String randomFilename = RandomUtils.getRandomString(testData.getRandomLength());
        String randomText = RandomUtils.getRandomString(testData.getRandomLength());
        createFileAndDelete(randomFilename, randomText);
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");
        Assert.assertFalse(mainScreen.isFileExits(randomFilename));
        mainScreen.reloadScreen();
        Assert.assertFalse(mainScreen.isFileExits(randomFilename));
    }

    @Test
    public void openFileCheckContentAndCloseFileTest() {
        String filename = testData.getFilename();
        String testText = testData.getFileText();
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");
        mainScreen.openFile(filename);

        Assert.assertTrue(fileOpeningScreen.waitForPageLoading(),
                "File Opening screen is not loaded");
        Assert.assertTrue(fileOpeningScreen.waitUntilSpinnerDisappears(
                testData.getTimeoutForOpening()), "File is not opened");
        Assert.assertTrue(textEditorScreen.waitForPageLoading(),
                "Editor Screen is not opened");

        String textFromEditor = textEditorScreen.getTextFromEditor();

        Assert.assertEquals(textFromEditor, testText, "Texts are not matched");
        textEditorScreen.closeEditor();
    }

    @Test
    public void searchFileTest() {
        String filename = testData.getFilename();
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");
        mainScreen.searchForContent(filename);

        SearchScreen searchScreen = new SearchScreen();
        Assert.assertTrue(searchScreen.isFileSearched(filename, testData.getTimeoutForSearch()),
                String.format("File %s was not found", filename));
    }

    private void createFileAndDelete(String fileName, String text) {
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");

        mainScreen.clickAddUploadDocument();
        mainScreen.choseFromCreateFileMenu(AddFileCategory.NEW_TEXT_DOCUMENT);

        createFileForm.createFile(fileName);

        Assert.assertTrue(fileOpeningScreen.waitForPageLoading(),
                "File Opening screen is not loaded");
        Assert.assertEquals(fileName, fileOpeningScreen.getFilename());
        Assert.assertTrue(fileOpeningScreen.waitUntilSpinnerDisappears(
                testData.getTimeoutForOpening()), "File is not opened");

        Assert.assertTrue(textEditorScreen.waitForPageLoading(),
                "Editor Screen is not opened");
        textEditorScreen.enterText(text);
        textEditorScreen.closeEditor();

        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");

        mainScreen.openContextMenu(fileName);
        mainScreen.choseFromFileContextMenu(ContextMenuCategory.DELETE);
        ContextMenuChoiceForm menuChoiceForm = new ContextMenuChoiceForm();
        Assert.assertTrue(menuChoiceForm.waitForPageLoading(), "Form is not opened");
        menuChoiceForm.clickDelete();
    }
}
