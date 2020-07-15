package Base;

import java.io.File;
import java.sql.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;

public class basePage extends page {

	
	private JavascriptExecutor jsExecutor;
	
	public basePage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElement(locator).getText();
	}

	@Override
	public WebElement getElement(By locator) {

		WebElement element = null;
		try {
			waitForElement(locator);
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Something went wrong when getting element");
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void waitForElement(By locator) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Some error/exception while waiting" + locator.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Some error/exception while waiting" + title);
			e.printStackTrace();
		}
	}

	@Override
	public void jsClick(WebElement element) {
		
		try {
			
			jsExecutor = (JavascriptExecutor)driver;
			
			jsExecutor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Some error/exception while waiting" + element.toString());
			e.printStackTrace();
		}
		
	}




}
