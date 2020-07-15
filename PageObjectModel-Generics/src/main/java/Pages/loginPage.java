package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.basePage;
import Base.BaseTest;
public class loginPage extends basePage{
	
	private JavascriptExecutor jsExecutor;

	
	private By userEmail = By.name("email");
	private By userPassword = By.name("password");
	private By sign_InButton = By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']");
	private By pageHeader= By.xpath("//div[contains(text(),'Use your email')]");
	private By InvalidValidation = By.xpath("//div[@class='Toastify__toast-body']");
	

	public loginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return the userEmail
	 */
	public WebElement getUserEmail() {
		return getElement(userEmail);
	}

	
	/**
	 * @return the userPassword
	 */
	public WebElement getUserPassword() {
		return getElement(userPassword);
	}


	/**
	 * @return the sign_In
	 */
	public WebElement getSign_In() {
		return getElement(sign_InButton);
	}

	/**
	 * @return the sign_In
	 */
	public WebElement getInvalidVal() {
		return getElement(InvalidValidation);
	}

	/**
	 * @return the pageHeader
	 */
	public WebElement getPageHeader() {
		return getElement(pageHeader);
	}

	//getting title
	public String getLoginPageTitle() {
		return getPageTitle();
	}
	
	//get loginPage header
	public String getLoginPageHeader() {
		return getPageHeader(pageHeader);
	}
	
	
	public boolean InvalidLoginCheck(String username,String password) {
		
		getUserEmail().sendKeys(username);
		BaseTest.test.get().info("Entered Incorrect userName "+ username);
		getUserPassword().sendKeys(password);
		BaseTest.test.get().info("Password entered as " + password);
		jsClick(getSign_In());
		jsClick(getSign_In());
		
		return getInvalidVal().isDisplayed();
	}
	//FunctionMethods
	public HomePage Login(String username,String password) throws InterruptedException {
		
		if(getTokenFromLocalStorage("CSRF-Token")!=null) {
			
			getUserEmail().sendKeys(username);
			BaseTest.test.get().info("Username entered as "+ username);
			getUserPassword().sendKeys(password);
			BaseTest.test.get().info("Password entered as " + password);
			Thread.sleep(3000);
			jsClick(getSign_In());
			jsClick(getSign_In());
			
			return getInstance(HomePage.class);
		}
		else {
			
			System.out.println("csrfToken is not generated, login failed");
			BaseTest.test.get().info("csrfToken is not generated, login failed");
			return null;
		}
		
	}
	
	//Validating csrfToken
	public String getTokenFromLocalStorage(String key) {

		jsExecutor = (JavascriptExecutor)driver;
		
		String csrfToken = (String) jsExecutor.executeScript(String.format("return window."
				+ "localStorage.getItem('%s');", key));
		System.out.println(csrfToken);
	
		return csrfToken;
	}
	
}
