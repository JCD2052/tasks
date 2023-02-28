import org.example.screens.FileOpeningScreen;
import org.example.screens.MainScreen;
import org.example.screens.SearchScreen;
import org.example.screens.TextEditorScreen;
import org.example.screens.categories.AddFileCategory;
import org.example.screens.categories.ContextMenuCategory;
import org.example.screens.forms.ContextMenuChoiceForm;
import org.example.screens.forms.CreateFileForm;
import org.testng.annotations.Test;

public class NextCloudTest {
    private final MainScreen mainScreen = new MainScreen();
    private final CreateFileForm createFileForm = new CreateFileForm();
    private final FileOpeningScreen fileOpeningScreen = new FileOpeningScreen();
    private final TextEditorScreen textEditorScreen = new TextEditorScreen();

    @Test
    public void createAndDeleteFileTest() {
        mainScreen.waitUntilSpinnerGone();

        mainScreen.clickAddUploadDocument();
        mainScreen.choseFromCreateFileMenu(AddFileCategory.NEW_TEXT_DOCUMENT);

        String someRandomName = "some random name";
        createFileForm.createFile(someRandomName);

        fileOpeningScreen.waitForPageLoading();
        fileOpeningScreen.isFilenameMatched(someRandomName);
        fileOpeningScreen.waitUntilSpinnerDisappears();

        textEditorScreen.waitForPageLoading();

        textEditorScreen.enterText("sadasdasdasdas");
        textEditorScreen.closeEditor();
        mainScreen.waitUntilSpinnerGone();

        mainScreen.openContextMenu(someRandomName);
        mainScreen.choseFromFileContextMenu(ContextMenuCategory.DELETE);
        ContextMenuChoiceForm menuChoiceForm = new ContextMenuChoiceForm();
        menuChoiceForm.waitForPageLoading();
        menuChoiceForm.clickDelete();
    }

    @Test
    public void openFileCheckContentAndCloseFileTest() {
        String filename = "";
        String testText = "";
        mainScreen.waitUntilSpinnerGone();
        mainScreen.openFile(filename);

        fileOpeningScreen.waitUntilSpinnerDisappears();
        String text = textEditorScreen.getTextFromEditor();
        text.equals(testText);
        textEditorScreen.closeEditor();
    }

    @Test
    public void searchFileTest() {
        String filename = "";
        mainScreen.waitUntilSpinnerGone();
        mainScreen.searchForContent(filename);
        SearchScreen searchScreen = new SearchScreen();
        searchScreen.isFileSearched(filename);
    }
}
