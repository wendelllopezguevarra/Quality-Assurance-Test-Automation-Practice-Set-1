package PageObjectTricentis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class LandingPageTricentisPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPageTricentisPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//*[@id=\"nav_automobile\"])[1]") //	@FindBy(id = "nav_automobile")
	WebElement nav_automobileButtonWebElement;
	
	public void openWebSite() {
		String expectedUrlString = "https://sampleapp.tricentis.com/101/#";
		driver.get(expectedUrlString); //	driver.get("https://sampleapp.tricentis.com/101/#");
		String actualUrlString = driver.getCurrentUrl();
		Assert.assertEquals(actualUrlString, expectedUrlString, "Url is not loaded successfuly."); //	Assert.assertEquals(actualUrlString, "12321", "Url is not loaded successfuly.");
	}
	
	public void nav_automobileButtonWebElementClick() {
		Assert.assertTrue(nav_automobileButtonWebElement.isEnabled(), "nav_automobileButton is not Clickable."); //	Assert.assertFalse(nav_automobileButtonWebElement.isEnabled(), "nav_automobileButton is Clickable." );
		nav_automobileButtonWebElement.click();
	}
	
}
