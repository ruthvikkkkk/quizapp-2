package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterResponse implements Serializable {

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("userId")
	private String userId;

	@SerializedName("refreshToken")
	private String refreshToken;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"accessToken = '" + accessToken + '\'' + 
			",userId = '" + userId + '\'' + 
			",refreshToken = '" + refreshToken + '\'' + 
			"}";
		}
}