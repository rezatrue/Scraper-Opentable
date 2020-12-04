import java.util.List;

public class Restaurant {
	
	String profile;
	String name;
	String description;
	
	String tweets;
	String phoneNumbers;
	List<String> delivery;
	String address;
	String hoursOfOperation;
	String cuisines;
	String paymentOptions;
	String additional;
	String website;
	
	public Restaurant() {
		
	}

	
	 List<Menu> menus;
	 
	 public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	} 
	  
// for xls 	 
/*
	String menus;
	
	 public String getMenus() {
			return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	} 
	
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<String> delivery) {
		this.delivery = delivery;
	}

	
	

	public String getTweets() {
		return tweets;
	}

	public void setTweets(String tweets) {
		this.tweets = tweets;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHoursOfOperation() {
		return hoursOfOperation;
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}

	public String getCuisines() {
		return cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public String getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(String paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	
	
}
