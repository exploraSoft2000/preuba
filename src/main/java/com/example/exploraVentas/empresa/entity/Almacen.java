package com.example.exploraVentas.empresa.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Almacen")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Almacen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlmacen;
	@NotNull
	private String nombre;
	@NotNull
	private String direccion;

	@JsonIgnoreProperties(value = {"almacenes", "hibernateLazyInitializer", "handler"} ,allowSetters = true)
	@ManyToOne()
	@JoinColumn(name = "idSucursal")
	private Sucursal sucursal;
	@NotNull
	private int idUsuario;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;
	
	
	public Almacen() {
		
	}
	
	@PrePersist
	public void prePersist() throws ParseException {
		
		Date dt= new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fcF=formatter.format(dt);
		this.fechaRegistro=formatter.parse(fcF);
	}
	@PreUpdate
	public void preUpdate() throws ParseException {
		
		Date dt= new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fcF=formatter.format(dt);
		this.fechaActualizacion=formatter.parse(fcF);
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
