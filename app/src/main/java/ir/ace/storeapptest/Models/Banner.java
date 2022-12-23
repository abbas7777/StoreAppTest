package ir.ace.storeapptest.Models;

public class Banner{
	private String id;
	private String pic;
	private String type;

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

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}
