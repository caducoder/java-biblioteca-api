package br.com.biblioteca.utils;

import java.util.ArrayList;
import java.util.List;

public class AuthResponse {

	private List<String> roles = new ArrayList<String>();
	private String accessToken;
	
	public List<String> getRoles() {
		return roles;
	}
	public void addRole(String role) {
		this.roles.add(role);
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
