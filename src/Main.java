import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;

import test.JsonGenerator;

public class Main {
	


	//final static String URL = "file:///F:/Scraping/Nov/opentable.com/restaurant03.html";
	//final static String URL = "file:///F:/Scraping/Nov/opentable.com/restaurant02.html";
	//final static String URL = "https://www.opentable.com/jaya-at-the-setai";
	//final static String URL = "https://www.opentable.com/r/casa-sensei-for-lauderdale";
	//final static String URL = "https://www.opentable.com/s?dateTime=2020-12-05T18%3A00%3A00&covers=2&metroId=17&regionIds%5B0%5D=320&neighborhoodIds%5B0%5D=483&latitude=26.124969&longitude=-80.120187";
	final static String URL = "file:///F:/Scraping/Nov/opentable.com/Ft.%20Lauderdale_OpenTable.html";
	
	public static void main(String[] args) {
		
		FireFoxHandaler fireFoxHandaler = new FireFoxHandaler();
		fireFoxHandaler.openBrowser();
		
		// for list scraping
		/*
		fireFoxHandaler.loadUrl(URL);
		fireFoxHandaler.takeList();
		fireFoxHandaler.closeBrowser();
		*/
		
		// for data scraping
		ListUrl listUrl = new ListUrl();
		LinkedList<String>  urls = listUrl.getListUrl();
		
		for (String url : urls) {
			fireFoxHandaler.getRestaurantData(url);
		}
		
		fireFoxHandaler.closeBrowser();
		
		
		LinkedList<Restaurant> infos = fireFoxHandaler.getRestaurantInfo();
		JsonGenerator jsonGenerator = new JsonGenerator("Ft.-Lauderdale");
		jsonGenerator.generateJsonFile(infos);
		
//		XlsWriter xlsWriter = new XlsWriter("Ft.-Lauderdale");
//		xlsWriter.generateXlsFile(infos);
		
	
		
	}
	
	public static void writeFile(String name, LinkedList<String> url) {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(name+".txt", "UTF-8");
			writer.println("[");
			for(int i=0; i< url.size(); i++) {
				writer.println("\"" + url.get(i) + "\", ");
			}
			writer.println("]");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
