import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FetchGradeReport {
	
	private String status;

	public String run(WebDriver driver, String path) {
		try {
			driver.switchTo(). defaultContent();
			driver.get("https://kiitportal.kiituniversity.net/irj/portal/");
			Thread.sleep(3000);
			try {
				driver.findElement(By.xpath("//*[@id=\"navNodeAnchor_1_1\"]")).click();
			}catch(Exception e){
				System.out.println("Not 4th year student");
			}
			driver.switchTo().frame("ivuFrm_page0ivu4");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[contains(text(),'Semester Grade Report')]")).click();
			Thread.sleep(3000);
			driver.switchTo().frame("isolatedWorkArea");
			Thread.sleep(1000);
			int count = 0;
			String[] years = {"2018-2019", "2019-2020", "2020-2021"};
			String[] sessions = {"Autumn", "Spring"};
			for(String year : years) {
				for(String session : sessions) {
					count++;
					driver.findElement(By.cssSelector("#WD29")).sendKeys(year);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#WD32")).sendKeys(session);
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("#WD40")).click();
					Thread.sleep(5000);
					saveGradeReport(path, " Semester" + count);
				}
			}
			path = path + "Grade Report\\";
			status = "Extracted to - " + path;
		}catch(Exception e) {
			status = "Failed";
		}
		return status;
	}

	public String saveGradeReport(String path, String rename) throws Exception {
		String filename = null;
		String downloadPath = path + "Downloads\\";
		String directoryName = path.concat("Grade Report");
	    File directory = new File(directoryName);
	    if (!directory.exists()){
	        directory.mkdir();
	    }
	    path = path + "Grade Report\\";
	    
	    File folder = new File(downloadPath);
	    File[] listOfFiles = folder.listFiles();
	    for (File file : listOfFiles) {
	        if (file.isFile()) {
	            filename = file.getName();
	        }
	    }
	    Files.move(Paths.get(downloadPath + filename), Paths.get(path + rename + ".pdf"));
	    Thread.sleep(2000);
	    return path;
	}
	
}
