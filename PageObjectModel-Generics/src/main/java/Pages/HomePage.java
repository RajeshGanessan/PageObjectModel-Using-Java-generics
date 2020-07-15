package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.basePage;

public class HomePage extends basePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	private By HomePageHeader = By.xpath("//div[contains(text(),'ESOP Overview')]");


	//get Homepage Header	
	public String getHomePageHeader() {
		return getPageHeader(HomePageHeader);
	}


	

}
