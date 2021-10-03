import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FetchDemandLetter {
	
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
			driver.findElement(By.xpath("//a[contains(text(),'Download Demand Letter')]")).click();
			Thread.sleep(3000);
			driver.switchTo().frame("isolatedWorkArea");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#WD1D")).click();
			Thread.sleep(5000);
			path = saveDemandLetter(path);
			status = "Extracted to - " + path;
		}catch(Exception e) {
			status = "Failed";
		}
		return status;
	}

	public String saveDemandLetter(String path) throws IOException {
		String filename = null;
		String downloadPath = path + "Downloads\\";
		String directoryName = path.concat("Demand Letter");
	    File directory = new File(directoryName);
	    if (!directory.exists()){
	        directory.mkdir();
	    }
	    path = path + "Demand Letter\\";
	    
	    File folder = new File(downloadPath);
	    File[] listOfFiles = folder.listFiles();
	    for (File file : listOfFiles) {
	        if (file.isFile()) {
	            filename = file.getName();
	        }
	    }
	    Files.move(Paths.get(downloadPath + filename), Paths.get(path + "Demand Letter.pdf"));
	    return path;
	}
	
}
