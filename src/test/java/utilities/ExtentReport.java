package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cucumber.api.Scenario;

public class ExtentReport {

    private static ExtentHtmlReporter configs;
    private static ExtentReports report;
    private static ExtentTest extentTest;

    static {
        configs = new ExtentHtmlReporter("./extentReport/report.html");
        configs.config().setTheme(Theme.DARK);
        configs.config().setDocumentTitle("Batch14-Extent-Report");
        report = new ExtentReports();
        report.attachReporter(configs);
        report.setSystemInfo("username","batch14");
        report.setSystemInfo("environtment","QA203");
    }

    public static void startTest(String  scenarioName  ){
        extentTest = report.createTest(scenarioName);
    }

    public static void pass(){
        extentTest.pass("PASS");
    }

    public static void fail(){
        extentTest.fail("FAIL");
    }

    public static void endReport(){
        report.flush();
    }
}
