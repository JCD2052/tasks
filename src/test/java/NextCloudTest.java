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

public class NextCloudTest {
    private final MainScreen mainScreen = new MainScreen();
    private final CreateFileForm createFileForm = new CreateFileForm();
    private final FileOpeningScreen fileOpeningScreen = new FileOpeningScreen();
    private final TextEditorScreen textEditorScreen = new TextEditorScreen();

    @Test
    public void createAndDeleteFileTest() {
        String randomFilename = RandomUtils.getRandomString(15);
        String randomText = RandomUtils.getRandomString(15);
        createFileAndDelete(randomFilename, randomText);
    }

    @Test
    public void createFileDeleteAndReload() {
        String randomFilename = RandomUtils.getRandomString(15);
        String randomText = RandomUtils.getRandomString(15);
        createFileAndDelete(randomFilename, randomText);
        Assert.assertFalse(mainScreen.isFileExits(randomFilename));
        //reload and check
        Assert.assertFalse(mainScreen.isFileExits(randomFilename));
    }

    @Test
    public void openFileCheckContentAndCloseFileTest() {
        String filename = "";
        String testText = "";
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");
        mainScreen.openFile(filename);

        Assert.assertTrue(fileOpeningScreen.waitForPageLoading(),
                "File Opening screen is not loaded");
        String text = textEditorScreen.getTextFromEditor();
        Assert.assertEquals(text, testText, "Texts are not matched");
        textEditorScreen.closeEditor();
    }

    @Test
    public void searchFileTest() {
        String filename = "";
        Assert.assertTrue(mainScreen.waitUntilSpinnerGone(), "Main Screen is not loaded.");
        mainScreen.searchForContent(filename);
        SearchScreen searchScreen = new SearchScreen();
        Assert.assertTrue(searchScreen.isFileSearched(filename),
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
        Assert.assertTrue(fileOpeningScreen.waitUntilSpinnerDisappears(),
                "File is not opened");

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
