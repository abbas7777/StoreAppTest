package ir.ace.storeapptest.Models;

import java.util.List;

public class FilterModel{
	private List<ValuesItem> values;
	private String title;

	public void setValues(List<ValuesItem> values){
		this.values = values;
	}

	public List<ValuesItem> getValues(){
		return values;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}