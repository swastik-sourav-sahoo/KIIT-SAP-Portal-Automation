import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

public class FetchStudentDetails {

	private String status;

	public String run(WebDriver driver, String path) throws IOException
	{
		try {
			String text = driver.getPageSource();
			Thread.sleep(3000);
			String data = "";
			String regex1 = "<span\\sid=\\\"WD20-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD22\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD27-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD29\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD2C-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD2E\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD33-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD35\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD38-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD3A\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD3D-text\\\"[^>]+>([^<]+).*?<span\\sid=\\\"WD3F\\\"[^>]+>([^<]+)";
			String regex2 = "<span\\sid=\\\"WD42-text\\\"[^>]+>([^<]+).*?<img\\sid=\\\"WD44\\\".*?title=\\\"(\\w+).*?<span\\sid=\\\"WD47-text\\\"[^>]+>([^<]+).*?<img\\sid=\\\"WD49\\\".*?title=\\\"(\\w+)";
			Matcher regex1Matcher = getMatches(regex1, text);
			Matcher regex2Matcher = getMatches(regex2, text);
			while(regex1Matcher.find()) {
				data = data + regex1Matcher.group(1) + " " + regex1Matcher.group(2) + "\n";
				data = data + regex1Matcher.group(3) + " " + regex1Matcher.group(4) + "\n";
				data = data + regex1Matcher.group(5) + " " + regex1Matcher.group(6).replaceAll("&nbsp;", "") + "\n";
				data = data + regex1Matcher.group(7) + " " + regex1Matcher.group(8) + "\n";
				data = data + regex1Matcher.group(9) + " " + regex1Matcher.group(10).replaceAll("&amp;", "&") + "\n";
				data = data + regex1Matcher.group(11) + " " + regex1Matcher.group(12) + "\n";
			}
			while(regex2Matcher.find()) {
				data = data + regex2Matcher.group(1) + " " + regex2Matcher.group(2) + "\n";
				data = data + regex2Matcher.group(3) + " " + regex2Matcher.group(4);
			}
			if(data.isEmpty()) {
				return "failed";
			}
			String directoryName = path.concat("Student Details");
			File directory = new File(directoryName);
			if (! directory.exists()){
				directory.mkdir();
			}
			try (PrintWriter out = new PrintWriter(path + "Student Details\\Student Details.txt")) {
				out.println(data);
			}
			status = "Successfully extracted to - " + path + "Student Details";
		}catch(Exception e) {
			status = "Failed";
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}

	public static Matcher getMatches(String regex, String text) {
		Pattern pattern = Pattern.compile(regex, (Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}

}
