package com.googleActRecognDataServer.api.postgres.pojos;

public class IdsCliente {
	
	private String advIdCliente;
	private String insIdCliente;
	
	public IdsCliente(String advIdCliente, String insIdCliente){
		this.advIdCliente = advIdCliente;
		this.insIdCliente = insIdCliente;
	}
	
	public IdsCliente(){}
	
	public String getAdvIdCliente() {
		return advIdCliente;
	}
	public void setAdvIdCliente(String advIdCliente) {
		this.advIdCliente = advIdCliente;
	}
	public String getInsIdCliente() {
		return insIdCliente;
	}
	public void setInsIdCliente(String insIdCliente) {
		this.insIdCliente = insIdCliente;
	}
	
	

}
