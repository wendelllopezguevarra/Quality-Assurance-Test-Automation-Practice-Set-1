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
