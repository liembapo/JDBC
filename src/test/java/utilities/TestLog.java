package utilities;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {
    public static void main(String[] args) {


        ExtentHtmlReporter configs = new ExtentHtmlReporter("./extentReport/report.html");
        configs.config().setTheme(Theme.DARK);

        ExtentReports report = new ExtentReports();
        report.attachReporter(configs);

        System.out.println("start test");
        ExtentTest extentTest = report.createTest("Google search");
        extentTest.pass("passed");
        extentTest.fail("failed");
        report.flush();
        System.out.println("completed" );
    }

}
