package com.example.exploraVentas.empresa.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Sucursal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSucursal;
	@NotNull
	private String nombre;
	@NotNull
	private String direccion;
	private String latitud;
	private String longitud;
	
	@JsonIgnoreProperties(value = {"sucursales", "hibernateLazyInitializer", "handler"} ,allowSetters = true)
	@ManyToOne()
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	
	@JsonIgnoreProperties(value = {"sucursales", "hibernateLazyInitializer", "handler"} ,allowSetters = true)
	@ManyToOne()
	@JoinColumn(name = "idDepartamento")
	private Departamento departamento;
	@NotNull
	private int idUsuario;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private String estado;

	 @JsonIgnoreProperties(value = {"sucursal", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	 @OneToMany(mappedBy = "sucursal" , cascade = CascadeType.ALL  , orphanRemoval = true)
	 private List<Almacen> almacenes;
	
	public Sucursal() {
		this.almacenes= new ArrayList<>();
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
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
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
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
	public List<Almacen> getAlmacenes() {
		return almacenes;
	}
	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes.clear();
		almacenes.forEach(p-> this.addAlmacen(p));
	}
	public void addAlmacen(Almacen almacen) {
		this.almacenes.add(almacen);
		almacen.setSucursal(this);
	}
	
	
}
