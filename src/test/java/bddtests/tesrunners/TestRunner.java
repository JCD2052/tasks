package bddtests.tesrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
//test line

@CucumberOptions(
        features = {"src/test/java/bddtests/features"},
        glue = {
                "bddtests/scenarios"
        })
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
