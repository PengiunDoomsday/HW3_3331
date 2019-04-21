package pricewatcher.base;

import java.io.*;

public class Item {
	/**Name of the item, use a good one, saved as a string */
	private String name;
	/** URL of the item, saved as a string */
	private String url;
	/** initial Price of the item, saved as a double */ 
	private double initialPrice;	
	private double currentPrice;
	private double priceChange;
	private String dateAdded;


	
	public Item(double initialPrice, String dateAdded)throws IOException {
		// contains initial price, and date added to price watcher
		this.initialPrice = initialPrice;
		this.dateAdded = dateAdded;
	}
	
	public  Item(String name, String url, double currentPrice, double priceChange) throws IOException {
		// contains name, url, current price
		this.name = name;
		this.url = url;
		this.currentPrice = currentPrice;
		this.priceChange = priceChange;	}
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	//Setter
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}
	
	
	//Getters
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public double getInitialPrice() {
		return initialPrice;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public double getPriceChange() {
		return priceChange;
	}
	
}