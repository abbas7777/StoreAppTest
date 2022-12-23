package ir.ace.storeapptest.Models;

public class CatsItem{
	private String parent;
	private String id;
	private String picurl;
	private String title;

	public void setParent(String parent){
		this.parent = parent;
	}

	public String getParent(){
		return parent;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPicurl(String picurl){
		this.picurl = picurl;
	}

	public String getPicurl(){
		return picurl;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}
