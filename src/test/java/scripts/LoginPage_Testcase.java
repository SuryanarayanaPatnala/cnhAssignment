package scripts;

import dataProvider.ConfigFileReader;
import dataProvider.ReadExcel;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginPageObjects;
import utilities.Screenshot;

import java.io.IOException;

@Listeners(utilities.Listeners.class)
public class LoginPage_Testcase {
    WebDriver driver;
    LoginPageObjects LoginPageObjects;
    ReadExcel readExcel;
    ExtentReport extentReport;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    void onStart() throws IOException {
        driver = DriverManager.getDriver();
        LoginPageObjects = new LoginPageObjects(driver);
        readExcel = new ReadExcel();


    }

    @Test(priority = 1, description = "checking login functionality with empty username and password")
    void test1() throws InterruptedException, IOException {
        extentReport = new ExtentReport();
        extentReport.createTest("checking login functionality with empty username and password");
        driver.get(ConfigFileReader.getUrl());
        extentReport.info("Browser successfully navigated to specified url");
        LoginPageObjects.usernamePlaceholder.sendKeys("");
        extentReport.info("username placeholder left with empty value");
        LoginPageObjects.passwordPlaceholder.sendKeys("");
        extentReport.info("password placeholder left with empty value");
        LoginPageObjects.rememberMeCheckbox.click();
        extentReport.info("login page rememberme checkbox is selected");
        LoginPageObjects.loginTab.click();
        extentReport.info("login page login button was clicked");
        Thread.sleep(4000);
        String strAlertMessage = LoginPageObjects.alertmessage.getText();
        boolean flag = LoginPageObjects.alertmessage.isDisplayed();
        if (flag == false) {
            extentReport.info("with empty username and password fields login was successful");
        } else {
            extentReport.info("login alert displayed as < Both Username and Password must be present >");
            Screenshot.snapshot(driver);
            extentReport.info("Screenshot was captured with the alert message");
            extentReport.fail("Test failed to login to the application without username and password");
            Assert.fail("testcase failed to log in to the application without username and password");

        }


    }


    @Test(priority = 2, description = "checking login functionality with valid username and password")
    void test2() throws IOException {
        extentReport.createTest("checking login functionality with valid username and password");
        driver.get(ConfigFileReader.getUrl());
        extentReport.info("Browser successfully navigated to specified url");
        LoginPageObjects.usernamePlaceholder.sendKeys(ConfigFileReader.getUsername());
        extentReport.info("valid username entered in username field");
        LoginPageObjects.passwordPlaceholder.sendKeys(ConfigFileReader.getpassword());
        extentReport.info("valid password entered in password field");
        LoginPageObjects.rememberMeCheckbox.click();
        extentReport.info("login page rememberme checkbox is selected");
        LoginPageObjects.loginTab.click();
        extentReport.info("login page login button was clicked");
        String str = LoginPageObjects.homepageCustomernameInfoField.getText();
        Assert.assertEquals(str, "John Doe");
        extentReport.info("After login successfully landed to homepage and verfied customer name using assertion");
        extentReport.pass("Testcase successfully with valid credentials");

    }

    @Test(priority = 3, description = "checking login functionality with some random usernames and passwords contains alphanumeric data from external excel.xlsx")
    void test3() throws InterruptedException {
        extentReport.createTest("checking login functionality with some random usernames and passwords contains alphanumeric data from external excel.xlsx");
        driver.get(ConfigFileReader.getUrl());
        extentReport.info("Browser successfully navigated to specified url");
        int lastrow = readExcel.getSheet().getLastRowNum();
        System.out.println(lastrow);
        for (int i = 0; i <= lastrow; i++) {

            for (int j = 0; j < 1; j++) {
                String username = readExcel.getSheet().getRow(i).getCell(j).toString();
                String password = readExcel.getSheet().getRow(i).getCell(j + 1).toString();
                System.out.println(username);
                System.out.println(password);
                LoginPageObjects.usernamePlaceholder.sendKeys(username);
                extentReport.info("random username entered in username field>>>" + username);
                LoginPageObjects.passwordPlaceholder.sendKeys(password);
                extentReport.info("random password entered in password field>>>" + password);
                LoginPageObjects.rememberMeCheckbox.click();
                extentReport.info("login page rememberme checkbox is selected");
                LoginPageObjects.loginTab.click();
                extentReport.info("login page login button was clicked");
                String str = LoginPageObjects.homepageCustomernameInfoField.getText();
                Assert.assertEquals(str, "John Doe");
                extentReport.info("After login successfully landed to homepage with random username and password");
                extentReport.pass("Testcase successfully with random credentials");
                driver.get(ConfigFileReader.getUrl());
            }

        }

    }

    @Test(priority = 4, description = "verifying login through social network button available in login webpage")
    void test4() {
        driver.get(ConfigFileReader.getUrl());
        extentReport.createTest("verifying login through social network button available in login webpage");

        LoginPageObjects.twitterButton.click();
        String actual = driver.getTitle();
        if (actual.equals("Demo App"))
            extentReport.fail("login through twitter button not responding");

        LoginPageObjects.facebookButton.click();
        String actual1 = driver.getTitle();
        if (actual1.equals("Demo App"))
            extentReport.fail("login through facebook button not responding");

        LoginPageObjects.linkedinButton.click();
        String actual2 = driver.getTitle();
        if (actual2.equals("Demo App"))
            extentReport.fail("login through linkedin button not responding");
    }

    @AfterTest
    void onFinish() {
        extentReport.flush();
    }
}

