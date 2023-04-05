package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AdsResponse implements Serializable {

	@SerializedName("cost")
	private int cost;

	@SerializedName("description")
	private String description;

	@SerializedName("productName")
	private String productName;

	@SerializedName("url")
	private String url;

	public void setCost(int cost){
		this.cost = cost;
	}

	public int getCost(){
		return cost;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"AdsResponse{" + 
			"cost = '" + cost + '\'' + 
			",description = '" + description + '\'' + 
			",productName = '" + productName + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}