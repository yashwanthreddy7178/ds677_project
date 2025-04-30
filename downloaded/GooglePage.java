package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends PageObjectsBases{

	public GooglePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "q")
	WebElement SeachBar;

	@FindBy(xpath = ("//div[@id=\"rso\"]/div[4]/div"))
	public WebElement ThirdResult;

	public void SendText(String Text) {
		SeachBar.sendKeys(Text);
		SeachBar.submit();	
	}

}
