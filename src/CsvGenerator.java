

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class CsvGenerator {

	public CsvGenerator() {
	}

	public int listtoCsv(LinkedList<Dentist> list) {
		
		int count = 0;
		
		System.out.println("list size : " + list.size());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = Calendar.getInstance();
		String fileName = dateFormat.format(cal.getTime());

		FileWriter writer = null;
		try {
			writer = new FileWriter("Findadentist.ada.org_list_" + fileName + ".csv");

			writer.append("ProfileUrl");
			writer.append(",");
			writer.append("Name");
			writer.append(",");
			writer.append("phone");
			writer.append(",");
			writer.append("Website");
			writer.append(",");
			writer.append("Address");
			writer.append(",");
			writer.append("Payment_options");
			writer.append(",");
			writer.append("Gender");
			writer.append(",");
			writer.append("Specialties");
			writer.append(",");
			writer.append("Language");
			writer.append(",");
			writer.append("Education");
			writer.append(",");
			writer.append("Practice_description");
			writer.append(",");
			writer.append("\n");

			System.out.println(" -- out size-- "+ list.size());
			if(list.size() > 0) {
				Iterator<Dentist> it = list.iterator();
	
				while (it.hasNext()) {
					Dentist dentist = (Dentist) it.next();
					try {
					System.out.println(dentist.getProfileUrl());
					System.out.println(dentist.getName());
					writer.append(csvformatDevider(dentist.getProfileUrl()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getName()));
					writer.append(",");
					writer.append("["+dentist.getPhone()+"]");
					writer.append(",");
					writer.append(csvformatDevider(dentist.getWebsite()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getAddress()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getPayment_options()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getInsurance()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getGender()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getSpecialties()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getLanguage()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getEducation()));
					writer.append(",");
					writer.append(csvformatDevider(dentist.getPractice_description()));
					writer.append("\n");
					count++;
				}catch (IOException e) {
					//..
				}
			}
			}		

		} catch (IOException e) {
			System.out.println(" csv g Error : " + e.getMessage());
		}finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return count;
	} 

	private String csvformatDevider(String text) {
		String newText; 
		try {
		newText = text.replaceAll("[\\t\\n\\r]", " ");
		
		if (newText.contains(","))
			if (!newText.startsWith("\"") && !newText.endsWith("\""))
				newText = "\"" + newText + "\"";
		}catch (Exception e) {
			return "";
		}
		return newText;
	}
	
	

}
