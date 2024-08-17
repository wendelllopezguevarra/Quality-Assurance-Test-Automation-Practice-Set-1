PART 1
	-Automate open website, click automobile button and close website
	-Assertion for validation
	-Page Object Model Framework
	

LEARNINGS	:QUALITY ASSURANCE TEST AUTOMATION, NO ALLURE REPORT, POM FRAMEWORK , ASSERTION
			:
How to Install Eclipse?
How to Install JDK? 
How to Install Maven?
How to Setup Environment Variables?

Open IDE (Eclipse)

Create new Maven project
	-next
	-Catalog: Maven Central
	-Filter: maven-archetype-quickstart
		-com.github.ywchang, 1.1
	-Set Group Id, Artifact Id, Package
		-GroupIdMaven100, ArtifactIdMaven100, GroupIdMaven100.ArtifactIdMaven100	
	-Click Finish
	-Type Y to Console tab
	-ArtifactIdMaven100 folder will appear to Project Explorer Tab

Build pom.xml
	-Open pom.xml file
	-Website: https://mvnrepository.com/
		-Search and get updated dependencies (Maven)
			-Selenium Java
			-WebDriverManager » 5.9.2
			-TestNG
			-SLF4J Simple Provider
		-Paste it to pom.xml under dependencies
		-Right Click maven project main folder
			-Maven, Update Project, Check Force Update...Release, Click OK

Add TestNg Library to the project
	-Right click main maven folder
	-Build path, Configure Build Path, Add Library..., TestNg, Next, Finish, Apply, Apply and Close

Run App.java
	-Run as Java Application
AppTest.java
	-Run as Junit Test
Copy AppTest.java and paste to same Package (make Apptest2.java)
	-Run as TestNg Test

START PROJECT	

Under scr/main/java
	-Create Package "AbstractComponents"
	-Under "AbstractComponents" Package, Create "AbstractComponent" Class
		-Check "inherited..." box
	-This code defines a base class called AbstractComponent for Selenium Page Objects.
		-Purpose: Serves as a common base class for page objects, allowing them to share common functionalities or utilities.
		-Constructor: Takes a WebDriver instance as an argument and assigns it to the driver field, enabling subclasses to interact with the browser.
		-Field: Contains a driver field to hold the WebDriver instance, which is used for performing actions and interactions on the web page.
		-By extending AbstractComponent, other page object classes can inherit the driver field and any common methods or utilities that may be added to this base class in the future.
	-"AbstractComponent" Class - Codes
----------------------------------------------------------------------------------------------------------------------------
package AbstractComponents;

import org.openqa.selenium.WebDriver;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
}

----------------------------------------------------------------------------------------------------------------------------

Under scr/main/java
	-Create Package "Resources"
	-Under "Resources" Package, Create "Global.properties" File
	-"Global.properties" File - Codes
----------------------------------------------------------------------------------------------------------------------------
browser=chrome
chromeDriverVersion=127.0.6533.120
----------------------------------------------------------------------------------------------------------------------------

Under scr/test/java
	-Create Package "BaseTest"
	-Under "AbstractComponents" Package, Create "BaseTestTricentis" Class
		-Setup Browser's Driver:
			-Declare Properties
			-FileInputStream to locate Global.propeties 
			-load FileInputStream to Properties
			-Declare Strings from Global.properties for easy use (what browser and version)
			-Create IfElse function
				-if browser.equalsIgnoreCase("chrome"), then
					WebDriverManager.chromedriver().browserVersion(chromeDriverVersion).setup();
					driver = new ChromeDriver(); 
			-Create @BeforeMtethod
				-Declare driver = initializeDriver(); to start driver initialization
			-Create @AfterMethod
				-driver.manage().deleteAllCookies();
				 driver.quit();
		-This code defines a base test class for Selenium tests with TestNG. Here's a quick explanation:
			-Imports: Includes necessary classes for file handling, WebDriver management, and TestNG annotations.
			-Class Declaration: BaseTestTricentis provides setup and teardown methods for Selenium tests.
			-initializeDriver() Method:
				-Loads browser configuration from a properties file (Global.properties).
				-Sets up the WebDriver based on the browser specified in the properties file.
				-Currently supports Chrome but includes placeholders for Firefox and Edge.
				-Configures the browser window and timeout settings.
			-openBrowser() Method:
				-Annotated with @BeforeMethod, it runs before each test method.
				-Calls initializeDriver() to set up the WebDriver instance.
				-Prints a log message.
			-closeBrowser() Method:
				-Annotated with @AfterMethod, it runs after each test method.
				-Cleans up by deleting cookies and quitting the browser.
				-Prints a log message.
			-This base class manages browser setup and teardown for Selenium tests, ensuring a consistent and clean test environment.
		-"BaseTestTricentis" Class - Codes
----------------------------------------------------------------------------------------------------------------------------
package BaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjectTricentis.LandingPageTricentisPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestTricentis {

	public LandingPageTricentisPage landingPageTricentisPage;

	public static WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\WELLA\\Desktop\\JAVA SELENUIM WENDELL"
				+ "\\major java folder\\ArtifactIdMaven100\\src\\main\\java\\Resources\\Global.properties");
		properties.load(fileInputStream);
		String browser = properties.getProperty("browser");
		String chromeDriverVersion = properties.getProperty("chromeDriverVersion");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().browserVersion(chromeDriverVersion).setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {

		} else if (browser.equalsIgnoreCase("edge")) {

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		return driver;
	}

	@BeforeMethod
	public void openBrowser() throws IOException { // Dito bubuksan ang browser at pupunta sa website
		driver = initializeDriver();

		landingPageTricentisPage = new LandingPageTricentisPage(driver);

		landingPageTricentisPage.openWebSite();

		System.out.println("SELENIUM AUTOMATION entered @BeforeMethod openBroser method..");
	}

	@AfterMethod
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
		System.out.println("SELENIUM AUTOMATION entered @BeforeMethod openBroser method..");
	}

}

----------------------------------------------------------------------------------------------------------------------------

Under scr/test/java
	-Create Package "TestCases"
		-Place where Test Cases are Implemented
	-Under "TestCases" Package, Create "TricentisRegistrationPositiveFlowTestCase" and "TricentisRegistrationNegativeFlowTestCase" Class
		-Check "inherited..." box
		-Extends to BaseTestTricentis 
	-This code defines a specific test case class for the Tricentis registration positive flow. Here’s a quick breakdown:
		-Class Declaration: TricentisRegistrationPositiveFlowTestCase extends BaseTestTricentis, inheriting its setup and teardown methods.
		-testRegistrationPositiveFlow() Method:	
			-Contains a placeholder for the actual test logic.
			-Currently, it only prints a message indicating that the test method has been entered.
		-This class is set up to use the browser initialization and cleanup methods provided by BaseTestTricentis and is intended for implementing the test steps for a positive registration flow in the Tricentis application.
	-"TricentisRegistrationPositiveFlowTestCase" Class - Codes
----------------------------------------------------------------------------------------------------------------------------
package TestCases;

import org.testng.annotations.Test;

import BaseTest.BaseTestTricentis;

public class TricentisRegistrationPositiveFlowTestCase extends BaseTestTricentis{
	@Test
	public void testRegistrationPositiveFlow() throws InterruptedException {

		landingPageTricentisPage.nav_automobileButtonWebElementClick();

		System.out.println("SELENIUM AUTOMATION entered testRegistrationPositiveFlow..");

	}
	
}

----------------------------------------------------------------------------------------------------------------------------

Under scr/main/java
	-Create Package "PageObjectTricentis"
	-Under "PageObjectTricentis" Package, Create "LandingPageTricentisPage" Class
		-Check "inherited..." box
	-This code defines a Selenium Page Object class for the Tricentis landing page
		-Imports: It imports necessary Selenium and TestNG classes for web interactions and assertions
		-Class Declaration: LandingPageTricentisPage extends AbstractComponent, inheriting functionalities from a base class likely for shared methods or utilities.
		-Constructor: Initializes the WebDriver and uses PageFactory to initialize elements defined in the class. 
			PageFactory simplifies element management by automating the initialization of elements annotated with @FindBy.
		-Element Locator: Uses @FindBy to locate the nav_automobile button on the page.
		-Methods:
			-openWebsite(): Navigates the browser to the Tricentis sample application URL.
			-nav_automobileClick(): Asserts that the nav_automobileBtn is enabled before clicking it.
	-"LandingPageTricentisPage" Class - Codes
----------------------------------------------------------------------------------------------------------------------------
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

----------------------------------------------------------------------------------------------------------------------------

END PROJECT
