package com.smartcourier.model;

public class LoginOut {

	LoginUser user;
	String errorMessage;
	
	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public static class LoginUser {
		String token;

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
		
		
	}
}
