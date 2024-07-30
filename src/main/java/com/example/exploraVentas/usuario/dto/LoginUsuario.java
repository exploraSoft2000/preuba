package com.example.exploraVentas.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUsuario {
	@NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;

    private int idOrganizacion;
    private int idUsuario;

    public LoginUsuario() {
		super();
	}

	public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(int idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}
}
