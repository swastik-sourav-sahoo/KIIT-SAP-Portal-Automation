import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Action {
	String status;
	String loginStatus;
	String attendanceStatus;
	String demandLetterStatus;
	String studentDetailStatus;
	String gradeReportStatus;
	String pageSource;
	WebDriver driver;
	WebElement element;

	public String run(String roll, String password) throws Exception {
		status = "Failed";
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\swast\\eclipse_workspace\\driver\\geckodriver.exe");

		String path = "C:\\Users\\swast\\Desktop\\";
		String directoryName = path.concat("SAP Extract");
		File directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}
		path = "C:\\Users\\swast\\Desktop\\SAP Extract\\";
		directoryName = path.concat(roll);
		directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}	
		path = "C:\\Users\\swast\\Desktop\\SAP Extract\\" + roll + "\\";
		directoryName = path.concat("Downloads");

		directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", path + "\\Downloads");
		profile.setPreference("pdfjs.disabled",true);
		profile.setPreference("browser.helperApps.alwaysAsk.force",false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.viewableInternally.enabledTypes", "");
		FirefoxOptions options = new FirefoxOptions().setProfile(profile);
		FirefoxDriver firefoxDriver = new FirefoxDriver(options);
		driver = firefoxDriver;

		Login login = new Login();
		status = login.run(roll, password, driver);
		if(!status.equals("Login Success"))
			return status;
		else {
			loginStatus = "Login Status : " + status;
		}

		FetchAttendance fetchAttendance = new FetchAttendance();
		status = fetchAttendance.run(driver, path);
		attendanceStatus = "Attendance Status : " + status;

		FetchStudentDetails fetchStudentDetails = new FetchStudentDetails();
		status = fetchStudentDetails.run(driver, path);
		studentDetailStatus = "Student Details Status : " + status;

		FetchDemandLetter fetchDemandLetter = new FetchDemandLetter();
		status = fetchDemandLetter.run(driver, path);
		demandLetterStatus = "Demand Letter Status : " + status;
		
		FetchGradeReport fetchGradeReport = new FetchGradeReport();
		status = fetchGradeReport.run(driver, path);
		gradeReportStatus = "Grade Report Status : " + status;

		status = "<html>" + loginStatus + "<br\\>" + attendanceStatus + "<br\\>" + demandLetterStatus + "<br\\>" + studentDetailStatus +  "<br\\>" + gradeReportStatus + "</html>";

		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.get("https://kiitportal.kiituniversity.net/irj/portal/");
		Thread.sleep(3000);
		driver.close();
		directory = new File(path + "Downloads");
		directory.delete();
		return status;
	}

}
