package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver ;

    public HomePage(WebDriver testdriver) {
        this.driver = testdriver;
        PageFactory.initElements(driver ,this) ;
    }

    @FindBy(linkText="Form Authentication")
    WebElement loginLink;

    public LoginPage clickFormAuthentication(){
        PageFactory.initElements(driver ,this) ;
        loginLink.click();
        return new LoginPage(driver);
    }





}
