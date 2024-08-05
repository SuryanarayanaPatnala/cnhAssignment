package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FitpeoPageObjects {

    @FindBy(xpath ="//a[.='Revenue Calculator']")
    public WebElement revenueCalculator;
    @FindBy(xpath="/html/body/div[1]/div[1]/header")
    public WebElement sliderSection;
    @FindBy(id =":R57alklff9da:")
    public WebElement sliderValvePlaceholder;
    @FindBy(xpath="/html/body/div[1]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]")
    public WebElement sliderHandle;
    @FindBy(xpath="/html/body/div[1]/div[1]/div[1]/div[1]/div/div[2]/p[2]")
    public WebElement updatedTextFieldValue;
    @FindBy(xpath = "//span[.='57']")
    public WebElement cpt99091;
    @FindBy(xpath = "//span[.='19.19']")
    public WebElement cpt99453;
    @FindBy(xpath = "//span[.='63']")
    public WebElement cpt99454;
    @FindBy(xpath = "//span[.='15']")
    public WebElement cpt99474;
    @FindBy(xpath="/html/body/div[1]/div[1]/div[1]/div[1]/div/div[3]/p[2]")
    public WebElement totalrecuringvalue;
    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/p[4]/p")
    public  WebElement TotalheaderDisplay;

    public FitpeoPageObjects(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
