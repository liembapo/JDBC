package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.google_pages.GoogleLandingPage;
import utilities.Configur;
import utilities.DriverSingleton;

import java.util.Properties;

public class SmartBear_StepDefs {

GoogleLandingPage.SmartBearLogin_page smartBearLogin_page =   new GoogleLandingPage.SmartBearLogin_page();


@Given("user on smart bear homepage")
    public void user_on_smart_bear_homepage(){
    DriverSingleton.getDriver().get(Configur.getProperty("smartbearUrl"));
}

@When("user input correct credential")
    public void user_input_correct_credential(){
    smartBearLogin_page.userNameInput.sendKeys(Configur.getProperty("smartbearUsername"));
    smartBearLogin_page.passwordInput.sendKeys(Configur.getProperty("smartbearPassword" ));
    smartBearLogin_page.loginButton.click();

}
@Then("user should see \"Welcome, Tester!\" displayed when logged in.")
    public void user_should_see_loginSuccessMessage_displayed() {
    WebElement message = DriverSingleton.getDriver().findElement(By.xpath("//div[@class ='login_info']"));

    Assert.assertTrue(message.isDisplayed());
}


    @Given("user on smart bear homepage2")
    public void user_on_smart_bear_homepage2(){
        DriverSingleton.getDriver().get(Configur.getProperty("smartbearUrl"));
    }

    @When("user input incorrect credential")
    public void user_input_incorrect_credential(){
        smartBearLogin_page.userNameInput.sendKeys(Configur.getProperty("smartbearUsername"));
        smartBearLogin_page.passwordInput.sendKeys("wrongPassword" + Keys.ENTER);

    }
    @Then("error message should be appear.")
    public void error_message_should_displayed() {
        boolean errorMessage = smartBearLogin_page.errorMessage.isDisplayed();

        Assert.assertTrue(errorMessage, "error message is displayed" );
    }
}
