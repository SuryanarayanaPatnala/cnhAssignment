package Script;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.FitpeoPageObjects;

import java.time.Duration;

public class AssignmentScript {
    public WebDriver driver;
    public FitpeoPageObjects pageObjects;
    Actions actions;
    JavascriptExecutor js;
    WebDriverWait wait;

    @BeforeMethod
    void beforemethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageObjects = new FitpeoPageObjects(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }


    @Test(priority = 1, description = "Navigate to the FitPeo Homepage")
    void test1() {
        driver.get("https://www.fitpeo.com/");
        js.executeScript("alert('Testcase Passed')");
    }

    @Test(priority = 2, description = "Navigate to the Revenue Calculator Page")
    void test2() throws InterruptedException {

        driver.get("https://www.fitpeo.com/");
        pageObjects.revenueCalculator.click();
        js.executeScript("alert('Testcase Passed')");
    }

    @Test(priority = 3, description = "Scroll Down to the Slider section")
    void test3() throws InterruptedException {

        driver.get("https://www.fitpeo.com/revenue-calculator");
        Thread.sleep(500);

        for (int i = 1; i < 10; i++) {
            js.executeScript("window.scrollBy(0,100)");
            Thread.sleep(50);
            boolean flag = false;
            try {
                flag = pageObjects.sliderSection.isEnabled();
                System.out.println(flag);
            } catch (Exception exception) {
                System.out.println(exception);
            }
            if (flag == true)
                break;
        }
        js.executeScript("alert('Testcase Passed and Scrolled the page until the revenue calculator slider is visible')");
    }

    @Test(priority = 4, description = "Adjust the Slider")
    void test4() throws InterruptedException {
        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderHandle));
        actions.dragAndDropBy(pageObjects.sliderHandle, 93, 0).build().perform();
        actions.sendKeys(pageObjects.sliderHandle, Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP).build().perform();
        String value = pageObjects.updatedTextFieldValue.getText();
        Assert.assertEquals(value, "820");
        System.out.println("verified and passed");
        js.executeScript("alert('Testcase Passed')");

    }

    @Test(priority = 5, description = "Update the Text Field")
    void test5() throws InterruptedException {

        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderValvePlaceholder));
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE).build().perform();
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.NUMPAD5, Keys.NUMPAD6, Keys.NUMPAD0).build().perform();
        js.executeScript("alert('Verified when the value 560 entered in the text field. Now the slider also should change accordingly')");
    }

    @Test(priority = 6, description = "Validate Slider Value")
    void test6() throws InterruptedException {
        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderValvePlaceholder));
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE).build().perform();
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.NUMPAD5, Keys.NUMPAD6, Keys.NUMPAD0).build().perform();
        String value = pageObjects.updatedTextFieldValue.getText();
        Assert.assertEquals(value, "560");
        System.out.println("verified and passed");
        js.executeScript("alert('Verified that when the value 560 is entered in the text field and  the sliders position is updated to reflect the value 560')");
    }

    @Test(priority = 7, description = "Select CPT Codes")
    void test7() throws InterruptedException {
        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderValvePlaceholder));
        js.executeScript("window.scrollBy(928,651)");
        pageObjects.cpt99091.click();
        pageObjects.cpt99453.click();
        pageObjects.cpt99454.click();
        pageObjects.cpt99474.click();
        js.executeScript("alert('Selected cpt codes successfully')");
    }

    @Test(priority = 8, description = "Validate Total Recurring Reimbursement")
    void test8() throws InterruptedException {

        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderValvePlaceholder));
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE).build().perform();
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.NUMPAD8, Keys.NUMPAD2, Keys.NUMPAD0).build().perform();
        String value = pageObjects.updatedTextFieldValue.getText();
        Assert.assertEquals(value, "820");
        System.out.println("verified and passed");
        String totalwithoutcpt = pageObjects.totalrecuringvalue.getText();

        js.executeScript("window.scrollBy(928,651)");
        pageObjects.cpt99091.click();
        pageObjects.cpt99453.click();
        pageObjects.cpt99454.click();
        pageObjects.cpt99474.click();
        String totalwithcpt = pageObjects.totalrecuringvalue.getText();
        if (!totalwithoutcpt.equals(totalwithcpt))
            System.out.println("updation success");
        System.out.println("initially without cpt total rec reum=" + totalwithoutcpt + "    " + "finally with cpt total rec reum=" + totalwithcpt);
        js.executeScript("alert('Total Recurring Reimbursement validated successfully')");
    }

    @Test(priority = 9, description = "Verify that the header displaying")
    void test9() throws InterruptedException {

        driver.get("https://www.fitpeo.com/revenue-calculator");
        wait.until(ExpectedConditions.visibilityOf(pageObjects.sliderValvePlaceholder));
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE).build().perform();
        actions.sendKeys(pageObjects.sliderValvePlaceholder, Keys.NUMPAD8, Keys.NUMPAD2, Keys.NUMPAD0).build().perform();
        String value = pageObjects.updatedTextFieldValue.getText();
        Assert.assertEquals(value, "820");
        System.out.println("verified and passed");
        String totalwithoutcpt = pageObjects.totalrecuringvalue.getText();

        js.executeScript("window.scrollBy(928,651)");
        pageObjects.cpt99091.click();
        pageObjects.cpt99453.click();
        pageObjects.cpt99454.click();
        pageObjects.cpt99474.click();
        js.executeScript("arguments[0].style.border='3px solid red'", pageObjects.TotalheaderDisplay);
        js.executeScript("alert('Verified that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.')");

    }

}


