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
@Table(name = "UsuarioRol")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioRol {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idUsuarioRol;
	  @JsonIgnoreProperties(value = {"usuarioRol", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "idRol")
	  	private Rol rol;
	  @JsonIgnoreProperties(value = {"usuarioRol", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "idUsuario")
	  	private Usuario usuario;
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

		public UsuarioRol() {
			
		}

		public int getIdUsuarioRol() {
			return idUsuarioRol;
		}

		public void setIdUsuarioRol(int idUsuarioRol) {
			this.idUsuarioRol = idUsuarioRol;
		}

		public Rol getRol() {
			return rol;
		}

		public void setRol(Rol rol) {
			this.rol = rol;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Date getFechaRegistro() {
			return fechaRegistro;
		}

		public void setFechaRegistro(Date fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}

		public Date getFechaActualizacion() {
			return fechaActualizacion;
		}

		public void setFechaActualizacion(Date fechaActualizacion) {
			this.fechaActualizacion = fechaActualizacion;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
	   	
}
