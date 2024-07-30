package com.example.exploraVentas.general.entity;

public class Mensaje {
	 private String mensaje;
	 private int status;
	 
	public Mensaje() {
		super();
	}

	public Mensaje(String mensaje) {
		this.mensaje=mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	
	
}
