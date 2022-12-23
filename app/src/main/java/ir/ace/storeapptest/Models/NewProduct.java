package ir.ace.storeapptest.Models;

public class NewProduct{
	private String price;
	private String pprice;
	private String id;
	private String pic;
	private String title;

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

	public void setPic(String pic){
		this.pic = pic;
	}

	public String getPic(){
		return pic;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}
