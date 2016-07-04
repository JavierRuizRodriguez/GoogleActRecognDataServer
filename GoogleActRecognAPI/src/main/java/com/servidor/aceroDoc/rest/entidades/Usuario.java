package com.servidor.aceroDoc.rest.entidades;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -7788619177798333712L;
	
	private String mail;
	private String username;
	private String password;
	private String name;
	private String last_name;
	private boolean enabled;
	
	public Usuario(){};

	public Usuario(String mail, String username,  String password,String name, String last_name, boolean enabled) {
		this.mail = mail;
		this.username = username;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.enabled = enabled;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Usuario [mail=" + mail + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", last_name="
				+ last_name + ", enabled=" + enabled + "]";
	}
	
	
	
	
}
