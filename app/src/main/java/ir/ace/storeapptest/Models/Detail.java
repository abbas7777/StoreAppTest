package ir.ace.storeapptest.Models;

import java.util.List;

public class Detail{
	private String image;
	private String pprice;
	private String garantee;
	private String rating;
	private String weight;
	private String title;
	private String colors;
	private String ratingItem;
	private String price;
	private List<Object> fav;
	private List<Object> bas;
	private String id;
	private String introduction;
	private String properties;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPprice(String pprice){
		this.pprice = pprice;
	}

	public String getPprice(){
		return pprice;
	}

	public void setGarantee(String garantee){
		this.garantee = garantee;
	}

	public String getGarantee(){
		return garantee;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setColors(String colors){
		this.colors = colors;
	}

	public String getColors(){
		return colors;
	}

	public void setRatingItem(String ratingItem){
		this.ratingItem = ratingItem;
	}

	public String getRatingItem(){
		return ratingItem;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setFav(List<Object> fav){
		this.fav = fav;
	}

	public List<Object> getFav(){
		return fav;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return introduction;
	}

	public void setProperties(String properties){
		this.properties = properties;
	}

	public String getProperties(){
		return properties;
	}

	public List<Object> getBas() {
		return bas;
	}

	public void setBas(List<Object> bas) {
		this.bas = bas;
	}
}