package bddtests.tesrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/bddtests/features"},
        glue = {
                "bddtests/stepdefenitions",
                "bddtests/hooks",
                "bddtests/transformations"
        })
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
