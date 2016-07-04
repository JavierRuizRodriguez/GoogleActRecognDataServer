package com.servidor.aceroDoc.rest.entidades;

public class Rol {
	private int identificador;
	private String mail;
	private String rol;
	
	public Rol(){};
	
	public Rol(int identificador, String mail, String rol) {
		
		this.identificador = identificador;
		this.mail = mail;
		this.rol = rol;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}		

}
