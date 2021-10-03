import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	
	private WebElement element;
	private String pageSource;
	private String status;
	
	public String run(String roll, String password, WebDriver driver) {
		try {
			driver.get("https://kiitportal.kiituniversity.net/irj/portal/");
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id=\"logonuidfield\"]"));
			element.clear();
			element.sendKeys(roll);
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"logonpassfield\"]"));
			element.clear();
			element.sendKeys(password);
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"certLogonForm\"]/table/tbody/tr[5]/td[2]/input"));
			element.click();
			Thread.sleep(3000);
			pageSource = driver.getPageSource();
			if(pageSource.contains("User authentication failed")) {
				return "Incorrect Credentials";
			}
			status = "Login Success";
		}catch(Exception e){
			status = "Login Failed";
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}
	
}
