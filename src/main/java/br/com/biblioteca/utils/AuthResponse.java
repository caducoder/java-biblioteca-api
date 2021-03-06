package br.com.biblioteca.utils;

import java.util.ArrayList;
import java.util.List;

public class AuthResponse {

	private String username;
	private List<Integer> roles = new ArrayList<Integer>();
	private String accessToken;
	
	public List<Integer> getRoles() {
		return roles;
	}
	public void addRole(Integer role) {
		this.roles.add(role);
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
