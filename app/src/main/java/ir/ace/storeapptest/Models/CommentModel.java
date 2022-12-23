package ir.ace.storeapptest.Models;

public class CommentModel{
	private String negative;
	private String passage;
	private String param;
	private String id;
	private String positive;
	private String suggest;
	private String title;
	private String likecount;
	private String user;
	private String dislikecount;

	public void setNegative(String negative){
		this.negative = negative;
	}

	public String getNegative(){
		return negative;
	}

	public void setPassage(String passage){
		this.passage = passage;
	}

	public String getPassage(){
		return passage;
	}

	public void setParam(String param){
		this.param = param;
	}

	public String getParam(){
		return param;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPositive(String positive){
		this.positive = positive;
	}

	public String getPositive(){
		return positive;
	}

	public void setSuggest(String suggest){
		this.suggest = suggest;
	}

	public String getSuggest(){
		return suggest;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setLikecount(String likecount){
		this.likecount = likecount;
	}

	public String getLikecount(){
		return likecount;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getUser(){
		return user;
	}

	public void setDislikecount(String dislikecount){
		this.dislikecount = dislikecount;
	}

	public String getDislikecount(){
		return dislikecount;
	}
}
