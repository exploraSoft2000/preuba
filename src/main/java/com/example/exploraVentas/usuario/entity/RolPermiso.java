package com.example.exploraVentas.usuario.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "RolPermiso")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RolPermiso {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idRolPermiso;
	  @JsonIgnoreProperties(value = {"rolPermiso", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "idRol")
	  	private Rol rol;
	  @JsonIgnoreProperties(value = {"rolPermiso", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "idPermiso")
	  	private Permiso permiso;
	    private Date fechaRegistro;
	    private Date fechaActualizacion;
	    private String estado;
	  @PrePersist
		public void prePersist() throws ParseException {

			Date dt = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF = formatter.format(dt);
			this.fechaRegistro = formatter.parse(fcF);
		}

		@PreUpdate
		public void preUpdate() throws ParseException {

			Date dt = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fcF = formatter.format(dt);
			this.fechaActualizacion = formatter.parse(fcF);
		}
	  
	public RolPermiso() {
		
	}
	public int getIdRolPermiso() {
		return idRolPermiso;
	}
	public void setIdRolPermiso(int idRolPermiso) {
		this.idRolPermiso = idRolPermiso;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Permiso getPermiso() {
		return permiso;
	}
	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}
	  
}
