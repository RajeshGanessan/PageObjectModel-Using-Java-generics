package TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BaseTest;

import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.loginPage;

public class loginTest extends BaseTest {

	// constructor for loading properties file ̑ ̑ ̑ ̑
	public loginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1, description = "LoginPage Landing")
	public void verifyLoginPageTitle() {

		String title = page.getInstance(loginPage.class).getLoginPageTitle();
		System.out.println(title);
		AssertJUnit.assertEquals(title, "MyStartupEquity | ESOP & CapTable Management Made Easy");
		test.get().info("Title verified sucessfully");
	}

	@Test(priority = 2, description = "Verifying invalid Credentials")
	public void verifyInvalidCredentials() {

		boolean validationCheck = page.getInstance(loginPage.class)
				.InvalidLoginCheck("rajesh.ganessan+hello00@" + "letsventure.com", "Admin@123");
		Assert.assertEquals(validationCheck, true);
		test.get().info("Invalid UserName/password validation is thrown succesfully");

	}

	@Test(priority = 3, description = "Login flow")
	public void logging_In() throws InterruptedException {

		HomePage homepage = page.getInstance(loginPage.class).Login("rajesh.ganessan+hello0@letsventure.com",
				"Admin@123");
		String homePageHeader = homepage.getHomePageHeader();
		System.out.println(homePageHeader);
		Assert.assertEquals(homePageHeader, "ESOP Overview");
		test.get().pass("Navigated to Dashboard Successfully");
	}

}
