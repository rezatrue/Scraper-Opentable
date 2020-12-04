import java.util.List;

public class Menu {
	
	String name;
	List<SubTitle> subTitles;
	
	public Menu() {	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubTitle> getSubTitles() {
		return subTitles;
	}

	public void setSubTitles(List<SubTitle> subTitles) {
		this.subTitles = subTitles;
	}

	
}

class SubTitle{
	String name;
	List<Item> items;
	
	public SubTitle(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
}





class Item{
	
	public Item(){	}
	
	String name;
	String description;
	
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
	

}