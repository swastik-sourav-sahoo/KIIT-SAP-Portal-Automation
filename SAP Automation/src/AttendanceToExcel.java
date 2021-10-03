import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttendanceToExcel {

	public static void run(String filename, String text, String path) throws IOException
	{
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet spreadsheet = workbook.createSheet("Attendance Details");

			XSSFRow row;

			Map<Integer, ArrayList<String>> attendanceData = new TreeMap<Integer, ArrayList<String>>();
			int count = 1;
			String headerRegex = "<div class=\\\"ls-sthcfocus\\s+urST5HCMetricContent urBorderBox urST5HCMetricSelColToggleWidth\\\"[^>]+><span[^<]+<[^>]+>([^<]+)</span>";
			Matcher headerMatcher = getMatches(headerRegex, text);
			ArrayList<String> obj = new ArrayList<>();
			while(headerMatcher.find()) {
				obj.add(headerMatcher.group(1));
			}
			attendanceData.put(count++, obj);
			String itemRegex = "<tr\\srr.*?class=\\\"lsCondensed\\s+urST5SelColUiGeneric\\\".*?<td.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td><td.*?<span[^<]+<span[^>]+>([^<]+)<.*?</td>";
			Matcher itemMatcher = getMatches(itemRegex, text);
			while(itemMatcher.find()) {
				obj = new ArrayList<>();
				obj.add(itemMatcher.group(1));
				obj.add(itemMatcher.group(2));
				obj.add(itemMatcher.group(3));
				obj.add(itemMatcher.group(4));
				obj.add(itemMatcher.group(5));
				obj.add(itemMatcher.group(6));
				obj.add(itemMatcher.group(7));
				obj.add(itemMatcher.group(8));
				obj.add(itemMatcher.group(9));
				attendanceData.put(count++, obj);
			}
			
			Set<Integer> keyid = attendanceData.keySet();

			int rowid = 0;

			for (int key : keyid) {

				row = spreadsheet.createRow(rowid++);
				ArrayList<String> objectArr = attendanceData.get(key);
				int cellid = 0;

				for (String cellValue : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue(cellValue);
				}
			}
			
			String directoryName = path.concat("Attendance Details");
			File directory = new File(directoryName);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
			FileOutputStream out = new FileOutputStream(new File(path + "Attendance Details\\" + filename + ".xlsx"));

			workbook.write(out);
			out.close();
	}
	
	public static Matcher getMatches(String regex, String text) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}
}