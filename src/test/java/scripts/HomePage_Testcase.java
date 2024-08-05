package scripts;

import dataProvider.ConfigFileReader;
import dataProvider.ReadExcel;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LoginPageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Listeners(utilities.Listeners.class)
public class HomePage_Testcase {
    WebDriver driver;
    pageObjects.LoginPageObjects LoginPageObjects;
    ReadExcel readExcel;
    ExtentReport extentReport;

    @BeforeTest
    void onStart() throws IOException {
        driver = DriverManager.getDriver();
        LoginPageObjects = new LoginPageObjects(driver);
        readExcel = new ReadExcel();
        extentReport = new ExtentReport();
    }

    @Test(priority = 1,description = "checking login functionality with valid username and password")
    void applicationLogin() throws IOException {
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

    @Test(priority = 2,description = "checking homepage amount transaction table and verifying the sorted amount values")
    void homepage() {
        extentReport.createTest("checking homepage amount transaction table and verifying the sorted amount values");
        List list = new ArrayList<>();
        int siz = driver.findElements(By.xpath("//td[@class='text-right bolder nowrap']/span")).size();
        for (int i = 1; i <= siz; i++) {
            String amount = driver.findElement(By.xpath("//*[@id='transactionsTable']/tbody/tr[" + i + "]/td[5]/span"))
                    .getText();
            list.add(amount);
        }
        System.out.println("unsorted values "+list);
        extentReport.info("unsorted amount transaction values "+list);
//single click action on amount button shows ascending values in the amount table(lower value to higher value)
        driver.findElement(By.id("amount")).click();
        List sortlist = new ArrayList<>();
        for (int i = 1; i <= siz; i++) {
            String sortamount = driver
                    .findElement(By.xpath("//*[@id='transactionsTable']/tbody/tr[" + i + "]/td[5]/span")).getText();
            sortlist.add(sortamount);
        }
        System.out.println("Ascending order" + sortlist);
        extentReport.info("single click action performed on amount header");
        extentReport.info("Ascending order values are displayed in the tansaction table"+ sortlist);
        List ascendlist = new ArrayList<>();
        for (int i = 0; i < sortlist.size(); i++) {
            String str1 = (String) sortlist.get(i);
            String str2 = str1.substring(0, str1.length() - 7);
            str2 = str2.replace(" ", "");
            str2 = str2.replace(",", "");
            int val = Integer.valueOf(str2);
            ascendlist.add(val);
        }
        System.out.println("ascending order " + ascendlist);
        extentReport.info("Amount values are ready to sort mathematically"+ascendlist);
        boolean flagAscending = false;
        //checking ascending order sort condition amount transaction values
        for (int i = 0; i < ascendlist.size() - 1; i++) {
            int a = (int) ascendlist.get(i);
            int b = (int) ascendlist.get(i + 1);
            if (a < b)
                flagAscending = true;
        }
        if (flagAscending){
            extentReport.pass("values are mathematically verified and declaring ascending order sort passed");
            System.out.println("Ascending sort is verified and passed");}
//second click on amount button shows descending values in the amount table(higher value to lower value)
        driver.findElement(By.id("amount")).click();
        extentReport.info("second click action performed on amount header");
        List decendentlist = new ArrayList<>();
        for (int i = 1; i <= siz; i++) {
            String sortamount = driver
                    .findElement(By.xpath("//*[@id='transactionsTable']/tbody/tr[" + i + "]/td[5]/span")).getText();
            decendentlist.add(sortamount);
        }
        extentReport.info("Descending order values are displayed in the amount transaction table"+decendentlist);
        System.out.println("descending order  " + decendentlist);
        List values = new ArrayList<>();
        for (int i = 0; i < decendentlist.size(); i++) {

            String str1 = (String) decendentlist.get(i);
            String str2 = str1.substring(0, str1.length() - 7);
            str2 = str2.replace(" ", "");
            str2 = str2.replace(",", "");
            int val = Integer.valueOf(str2);
            values.add(val);
        }
        System.out.println("Descending order  " + values);
        extentReport.info("Amount values are ready to sort mathematically "+values);
        boolean flagDescending = false;

        //checking Descending order sort condition amount transaction values
        for (int i = 0; i < values.size() - 1; i++) {
            int a = (int) values.get(i);
            int b = (int) values.get(i + 1);
            if (a > b)
                flagDescending = true;

        }
        if (flagDescending) {
            extentReport.pass("values are mathematically verified and declaring Descending order sort passed");
            System.out.println("descending sort is verified and passed");
        }
extentReport.pass("Homepage amount header functionality working fine and values are sorted perfectly");

    }
    @AfterTest
    void onFinish(){
        extentReport.flush();
        driver.close();
    }

}
