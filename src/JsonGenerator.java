
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonGenerator {
	
	String fileName = "";
	
	public JsonGenerator(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("deprecation")
	public void generateJsonFile(LinkedList<Restaurant> infos) {
		
		JSONArray resList = new JSONArray();
		
		for(int m = 0; m < infos.size(); m++) {
			System.out.println("loop : " + m);
			Restaurant restaurant = infos.get(m);
			
			JSONObject resDetails = new JSONObject();
			
				resDetails.put("Name", restaurant.getName());
				resDetails.put("Description", restaurant.getDescription());
				resDetails.put("Tweets", restaurant.getTweets());
				resDetails.put("Phone Number", restaurant.getPhoneNumbers());
	
				resDetails.put("Address", restaurant.getAddress());
				resDetails.put("Hours Of Operation", restaurant.getHoursOfOperation());
				resDetails.put("Cuisines", restaurant.getCuisines());
				resDetails.put("Payment Options", restaurant.getPaymentOptions());
				resDetails.put("Additional", restaurant.getAdditional());
				resDetails.put("Website", restaurant.getWebsite());
				
				JSONArray deliveryArray = new JSONArray(); 
				List<String> deliveryList = restaurant.getDelivery();
				
				for (int i = 0; i < deliveryList.size(); i++) {
					deliveryArray.add(deliveryList.get(i));
				}
				resDetails.put("delivery", deliveryArray);
			
				 
				List<Menu> menuList = restaurant.getMenus();
				JSONArray nemuArray = new JSONArray();
				for (int i = 0; i < menuList.size(); i++) {
							JSONObject menusObj = new JSONObject();

							JSONObject subTitlesObj = new JSONObject(); 
							List<SubTitle> subTitleList = menuList.get(i).getSubTitles();
							
							for (int j = 0; j < subTitleList.size(); j++) {
								JSONObject subTitleObj = new JSONObject();

								List<Item> items = subTitleList.get(j).getItems();
									JSONArray itemsArray = new JSONArray(); 
									for (int k = 0; k < items.size(); k++) {

										JSONObject itemObj = new JSONObject();
										itemObj.put("Item", items.get(k).getName());
										itemObj.put("Description", items.get(k).getDescription());
									itemsArray.add(itemObj);	
									}
								
							subTitlesObj.put(subTitleList.get(j).getName(),itemsArray);	
							}
							
				menusObj.put(menuList.get(i).getName(), subTitlesObj);
				nemuArray.add(menusObj);				
				}
				
				resDetails.put("Menu", nemuArray);
				
				
			JSONObject resObject = new JSONObject(); 
			resObject.put("restaurant", resDetails);
		
		resList.add(resObject);
		}
		
		System.out.println(" ---- writing json ----");
        //Write JSON file
        try (FileWriter file = new FileWriter(fileName +".json")) {
 
            file.write(resList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
