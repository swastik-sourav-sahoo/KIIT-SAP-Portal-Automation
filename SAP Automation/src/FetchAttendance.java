import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FetchAttendance {
	
	private String pageSource;
	private String status;

	public String run(WebDriver driver, String path) {
		try {
			try {
				driver.findElement(By.xpath("//*[@id=\"navNodeAnchor_1_1\"]")).click();
				Thread.sleep(2000);
			}catch(Exception e){
				System.out.println("Not 4th year student");
			}
			driver.switchTo().frame("ivuFrm_page0ivu4");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[contains(text(),'Student Attendance Details')]")).click();
			Thread.sleep(5000);
			driver.switchTo().frame("isolatedWorkArea");
			Thread.sleep(1000);
			String[] years = {"2018-2019", "2019-2020", "2020-2021"};
			String[] sessions = {"Autumn", "Spring"};
			for(String year : years) {
				for(String session : sessions) {
					driver.findElement(By.cssSelector("#WD5C")).sendKeys(year);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#WD72")).sendKeys(session);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#WD7F")).click();
					Thread.sleep(3000);
					pageSource = driver.getPageSource();
					Thread.sleep(3000);
					AttendanceToExcel.run(year + " " + session, pageSource, path);
				}
			}
			status = "Extracted to - " + path + "Attendance Details";
		}catch(Exception e) {
			status = "Failed";
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}
	
}
