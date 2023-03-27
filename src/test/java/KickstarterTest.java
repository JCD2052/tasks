import org.jcd2052.config.PropertyConfig;
import org.jcd2052.config.TestDataConfig;
import org.jcd2052.models.ProjectInfo;
import org.jcd2052.screens.MainScreen;
import org.jcd2052.screens.ProjectScreen;
import org.jcd2052.screens.SearchScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KickstarterTest extends BaseTest {
    private final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();
    private final MainScreen mainScreen = new MainScreen();
    private final SearchScreen searchScreen = new SearchScreen();
    private final ProjectScreen projectScreen = new ProjectScreen();

    @Test
    public void getProjectSearchAndMatchTest() {
        Assert.assertTrue(mainScreen.waitForLoading(), "Main Screen has been not loaded.");

        mainScreen.swipeToTheNextTab();
        Assert.assertTrue(mainScreen.waitForProjectsLoading(),
                "Project list have been not loaded.");
        mainScreen.swipeDown();

        ProjectInfo mainScreenProjectInfo = mainScreen.getProjectInfo(testData.getPosition());
        String projectName = mainScreenProjectInfo.getProjectName();

        mainScreen.swipeToShowSearch();
        mainScreen.clickToSearch();

        Assert.assertTrue(searchScreen.waitForLoading(),
                "Screen page has been not loaded.");
        searchScreen.searchProject(projectName);

        ProjectInfo searchScreenProjectInfo = searchScreen.getProjectInfo(projectName);
        Assert.assertEquals(mainScreenProjectInfo, searchScreenProjectInfo,
                "Results are not same");

        searchScreen.selectProject(projectName);
        Assert.assertTrue(projectScreen.waitForLoading(),
                "Project page has been not loaded.");

        String projectScreenDeadline = projectScreen.getProjectDeadline();
        Assert.assertEquals(projectScreenDeadline, mainScreenProjectInfo.getProjectDeadline(),
                "Deadlines are not matched.");
    }
}
