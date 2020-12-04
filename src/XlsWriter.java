import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsWriter {
	private final String fileName;
	
	public XlsWriter(String name) {
		this.fileName = name;
	}
	
	// https://www.codejava.net/coding/how-to-write-excel-files-in-java-using-apache-poi
	public void generateXlsFile(LinkedList<Restaurant> data) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Restaurants");
		
		Iterator<Restaurant> it = data.iterator();
		
		int rowCount = 0;
		Row row = sheet.createRow(rowCount);
		
			Cell cellh0 = row.createCell(1);
			cellh0.setCellValue("OpenTable_URL");
			Cell cellh1 = row.createCell(1);
			cellh1.setCellValue("Name");
			Cell cellh2 = row.createCell(2);
			cellh2.setCellValue("Description");
			Cell cellh3 = row.createCell(3);
			cellh3.setCellValue("Menu");
			Cell cellh4 = row.createCell(4);
			cellh4.setCellValue("Tweets");
			Cell cellh5 = row.createCell(5);
			cellh5.setCellValue("Phone_numbers");
			Cell cellh6 = row.createCell(6);
			cellh6.setCellValue("Delivery");
			Cell cellh7 = row.createCell(7);
			cellh7.setCellValue("Address");
			Cell cellh8 = row.createCell(8);
			cellh8.setCellValue("Hours-of_operation");
			Cell cellh9 = row.createCell(9);
			cellh9.setCellValue("Cuisines");
			Cell cellh10 = row.createCell(10);
			cellh10.setCellValue("Payment_options");
			Cell cellh11 = row.createCell(11);
			cellh11.setCellValue("Additional");
			Cell cellh12 = row.createCell(12);
			cellh12.setCellValue("Website");
		

		while (it.hasNext()) {
			
			row = sheet.createRow(++rowCount);
			Restaurant singleData =  it.next();
			int columnCount = 0;
			
				String profile = singleData.getProfile()();
				String name = singleData.getName();
				String description = singleData.getDescription();
				String menu = singleData.getMenus();
				String tweets = singleData.getTweets();
				String phoneNumbers = singleData.getPhoneNumbers();
				String delivery = singleData.getDelivery().toArray().toString();
				String address = singleData.getAddress();
				String hoursOfOperation = singleData.getHoursOfOperation();
				String cuisines = singleData.getCuisines();
				String paymentOptions = singleData.getPaymentOptions();
				String additional = singleData.getAdditional();
				String website = singleData.getWebsite();
				
				Cell cell0 = row.createCell(columnCount, CellType.STRING);
				    cell0.setCellValue(profile);
				
				Cell cell1 = row.createCell(++columnCount, CellType.STRING);
					cell1.setCellValue(name);
				
				Cell cell2 = row.createCell(++columnCount, CellType.STRING);
					cell2.setCellValue(description);
				
				Cell cell3 = row.createCell(++columnCount, CellType.STRING);
					cell3.setCellValue(menu);
					
					Cell cell4 = row.createCell(++columnCount, CellType.STRING);
					cell4.setCellValue(tweets);
				
				Cell cell5 = row.createCell(++columnCount, CellType.STRING);
					cell5.setCellValue(phoneNumbers);
				
				Cell cell6 = row.createCell(++columnCount, CellType.STRING);
					cell6.setCellValue(delivery);
				
					Cell cell7 = row.createCell(++columnCount, CellType.STRING);
					cell7.setCellValue(address);
				
				Cell cell8 = row.createCell(++columnCount, CellType.STRING);
					cell8.setCellValue(hoursOfOperation);
				
				Cell cell9 = row.createCell(++columnCount, CellType.STRING);
					cell9.setCellValue(cuisines);
					
					Cell cell10 = row.createCell(++columnCount, CellType.STRING);
					cell10.setCellValue(paymentOptions);
				
				Cell cell11 = row.createCell(++columnCount, CellType.STRING);
					cell11.setCellValue(additional);
				
				Cell cell12 = row.createCell(++columnCount, CellType.STRING);
					cell12.setCellValue(website);	

					
			
		}
		
		try (FileOutputStream outputStream = new FileOutputStream(fileName + ".xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
