package ir.ace.storeapptest.Models;

import com.google.gson.annotations.SerializedName;

public class Product{
	private String price;
	private String pprice;
	private String id;
	private String image;
	private String title;
	@SerializedName("filter_item")
	private String filterItem;

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setPprice(String pprice){
		this.pprice = pprice;
	}

	public String getPprice(){
		return pprice;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public String getFilterItem() {
		return filterItem;
	}

	public void setFilterItem(String filterItem) {
		this.filterItem = filterItem;
	}
}
