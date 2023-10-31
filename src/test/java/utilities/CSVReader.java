package utilities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVReader {

	private static CSVParser csvParser;
	private static CSVFormat csvFormat;
	private static String[] HEADERS = {"TestCase","username","password"};
	private static Iterable<CSVRecord> records;
	
	public CSVReader(String filePath) {
		getCSVMap(filePath);
	}
	
	@SuppressWarnings("deprecation")
	private static void getCSVMap(String filePath) {
		try(Reader reader = new FileReader(filePath)){
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
//			csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
//			records = csvFormat.parse(reader);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getData(String id, String dataType) {
		String extracted = null;
		for (CSVRecord csvRecord : csvParser) {
//		for (CSVRecord csvRecord : records) {
            // Accessing values by column index
            String column1 = csvRecord.get(0);
            if(column1.equalsIgnoreCase(id)) {
            	extracted = csvRecord.get(dataType);
            	break;
            }
        }
		if(extracted == null) {
			throw new Error("Data not found in file!");
		}
		return extracted;
	}
}
