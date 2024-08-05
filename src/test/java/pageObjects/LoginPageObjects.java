package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
    @FindBy(id = "username")
    public WebElement usernamePlaceholder;
    @FindBy(id = "password")
    public WebElement passwordPlaceholder;
    @FindBy(id = "log-in")
    public WebElement loginTab;
    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-warning']")
    public WebElement alertmessage;
    @FindBy(className = "form-check-input")
    public WebElement rememberMeCheckbox;

    @FindBy(how = How.ID, using = "logged-user-name")
    public WebElement homepageCustomernameInfoField;

    @FindBy(xpath = "/html/body/div/div/form/div[3]/div[2]/a[1]")
    public WebElement twitterButton;

    @FindBy(xpath = "/html/body/div/div/form/div[3]/div[2]/a[2]")
    public WebElement facebookButton;
    @FindBy(xpath = "/html/body/div/div/form/div[3]/div[2]/a[2]")
    public WebElement linkedinButton;


    public LoginPageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
