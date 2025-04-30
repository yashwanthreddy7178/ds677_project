package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ShiftingContentImagePage {

    private WebDriver driver;
    int xCordBefore, xCordAfter;

    @FindBy(how = How.LINK_TEXT, using = "Example 2: An image")
    private WebElement correctPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/p[3]/a")
    private WebElement clickHere;

    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div/img")
    private WebElement Image;

    public ShiftingContentImagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void gotoPage() {
        correctPage.click();
    }

    public void click() {
        clickHere.click();
    }

    public void getCoordinatesBeforeClick() {
        Point classname = Image.getLocation();
        xCordBefore = classname.getX();
    }

    public void getCoordinatesAfterClick() {
        clickHere.click();
        Point classname = Image.getLocation();
        xCordAfter = classname.getX();
    }

    public void compare() {
        assertThat(xCordBefore, is(not(xCordAfter)));
    }
}
