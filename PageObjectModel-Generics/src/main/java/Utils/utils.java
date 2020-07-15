package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseTest;



public class utils extends BaseTest {

	
	public static String takeScreenshot() throws IOException {
		
		Date date = new Date();
		
		long currentTime = date.getTime();
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/src/main/resources/Screenshots"+currentTime+".png";
				
		FileUtils.copyFile(screenshot, new File(path));
		return path;
	}
}
