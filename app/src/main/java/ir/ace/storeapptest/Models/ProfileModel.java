package ir.ace.storeapptest.Models;

public class ProfileModel {
	private String address;
	private String name;
	private String codeposti;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCodeposti(String codeposti){
		this.codeposti = codeposti;
	}

	public String getCodeposti(){
		return codeposti;
	}
}
