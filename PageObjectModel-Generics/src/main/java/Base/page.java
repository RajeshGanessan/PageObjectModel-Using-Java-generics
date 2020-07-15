package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class page	{

	
	protected WebDriver driver;
	WebDriverWait wait;
	
	
	public page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		}
	
	public abstract String getPageTitle();
	
	public abstract String getPageHeader(By locator);
	
	public abstract WebElement getElement(By locator);
	
	public abstract void waitForElement(By locator);
	
	public abstract void waitForPageTitle(String title);    
	
	public abstract void jsClick(WebElement element);
	
	

	
	public <Tpage extends basePage> Tpage getInstance(Class<Tpage> pageClass)	{
		 
		try {
			
		return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
	} 
		catch (Exception e) 
		{
		e.printStackTrace();
	}
		return null;
	}
}
