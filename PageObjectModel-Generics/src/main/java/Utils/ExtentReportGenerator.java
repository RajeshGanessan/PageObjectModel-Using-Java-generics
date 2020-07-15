package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.BaseTest;



public class ExtentReportGenerator extends BaseTest {
	
	public ExtentReportGenerator() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	static Calendar instance = Calendar.getInstance();
	static Date time = instance.getTime();
	
	private static ExtentReports extent;
    private static Platform platform;
    private static String reportFileName = "ExtentReports-Version3-Test-Automation-Report"+time+".html";
    private static String linuxPath = System.getProperty("user.dir")+ "/Reports";
    private static String windowsPath = System.getProperty("user.dir")+ "\\Reports";
    private static String macPath = System.getProperty("user.dir")+ "/Reports";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String linuxReportFileLoc = linuxPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
 
    public static ExtentReports getInstance()  {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance(){
    	platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.setAppendExisting(false);
        //htmlReporter.
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
 
        extent = new ExtentReports();
       
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("Host Name",System.getProperty("user.name"));
        extent.setSystemInfo("Java Version",System.getProperty("java.version"));

        extent.attachReporter(htmlReporter);
       // extent.set
 
        return extent;
    }
 
    //Select the extent report file location based on platform
    private static String getReportFileLocation (Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case LINUX:
                reportFileLocation = linuxReportFileLoc;
                createReportPath(linuxPath);
                System.out.println("ExtentReport Path for Linux: " + linuxPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            case MAC:
            	reportFileLocation = macReportFileLoc;
            	createReportPath(macPath);
            	System.out.println("ExtentReport Path for Mac:" + macPath + "/n");
            	break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }
 
    //Create the report path if it does not exist
    private static void createReportPath (String path) {
    	
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }
 
    //Get current platform
    private static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
    

}
