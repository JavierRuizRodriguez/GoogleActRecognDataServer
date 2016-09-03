package com.googleActRecognDataServer.api.postgres.pojos;

import org.rosuda.JRI.Rengine;

public class MotorR {

	private static MotorR instancia = null; 
	private final Rengine motorR = new Rengine(new String[] { "-vanilla -no-save" }, false, null);
	
	protected MotorR(){}
	
	public static MotorR getInstacia(){
		if(instancia == null)
			instancia = new MotorR();
		return instancia;
	}
	
	public Rengine getMotorR(){
		return motorR;
	}
}
