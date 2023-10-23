package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	private static int dataRowSize;
	private static Map<String, HashMap<String,String>> testDataMap = new LinkedHashMap<String, HashMap<String, String>>();
	
	public ExcelReader(String filePath, String sheetName) {
		System.out.println(filePath);
		getDataSheetMap(filePath,sheetName);
	}
	
	private static void getDataSheetMap(String filePath, String sheetName) {
		List<String> columnNames = new ArrayList<String>();
		
		try {
			FileInputStream dataFile = new FileInputStream(filePath);
			XSSFWorkbook wbook = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = wbook.getSheet(sheetName);
			wbook.close();
			
			// GET COLUMN HEADERS AS KEYS TO HASHMAP
			Iterator<Row> rows = sheet.iterator();
			Row rowHeader = rows.next();
			Iterator<Cell> header = rowHeader.iterator();
			Cell headerValue;
			
			while(header.hasNext()) {
				headerValue = header.next();
				columnNames.add(getValue(headerValue));
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
