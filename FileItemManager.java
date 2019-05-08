package pricewatcher.base;

import java.io.BufferedWriter;

import org.json.simple.JSONArray;

public class FileItemManager {
	private static FileItemManager theinstance;
	
	private FileItemManager() {
		
	}
	
	public static FileItemManager getInstance() {
		if(theInstance == null) {
			theInstance = new FileItemManager();
		}
		return theInstance;
	}
	
	public void start() {
		restore();
	}
	
	public Item add(String name, String url, double price, double dateAdd) {
		Item item = super.add(name,url,price,dateAdd);
		if(item != null) {
			save();
		}
		return item;
	}
	
	@Override
	public Item updatePrice(String url,float price) {
		Item item = super.updatePrice(url,price);
		if(item != null) {
			save();
		}
		return item;
	}
	
	protected void save() {
		try(BufferedWriter write = nre BufferedWriter(new FileWriter(Constants.DATA_FILE))){
			JSONArray arr = new JSONArray();
			for(Item e: items()) {
				arr.put(e.toJson());
			}
			write.write(arr.toString());
		}
		catch(IOException e) {
			
		}
	}
	
}
