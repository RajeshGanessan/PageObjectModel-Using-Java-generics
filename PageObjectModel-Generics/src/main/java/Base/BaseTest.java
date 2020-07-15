package Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.awt.print.Pageable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utils.ExtentReportGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver;
	public page page;
	public static Properties prop;

	public static ExtentReports extent = ExtentReportGenerator.createInstance();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
//    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	// FileInputstream

//    basePage base = new basePage(driver);

	public static void FileInit() throws IOException {

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Data.properties");
			prop = new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {

			System.out.println("Error Loading the properties file");
			e.printStackTrace();
		}
	}

//	
	// Setting up Browser
	@BeforeMethod
	public void setUp() throws IOException {

		//initializing properties
		FileInit();
		
		System.out.println(prop.getProperty("browser"));

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		else {

			System.out.println("Browser is not supported, supported only chrome and firefox");
		}

		driver.get(prop.getProperty("TestUrl"));
//		driver.get("http://inedgetest.heptagon.tech");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.manage().deleteAllCookies();

		page = new basePage(driver);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();


	}

	//for quitting 
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

	

}
