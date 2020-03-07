package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DriverSingleton;
import utilities.ExtentReport;
import utilities.TempStorage;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        //anything you want to run before each scenario
        ExtentReport.startTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario){
//        System.out.println(scenario.getName());
//        System.out.println(scenario.getStatus());

        if(scenario.isFailed()){
            byte [] screenShot = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot,"image/png");
            ExtentReport.fail();
        }else {
            ExtentReport.pass();
        }

        DriverSingleton.closeDriver();
    }
}
