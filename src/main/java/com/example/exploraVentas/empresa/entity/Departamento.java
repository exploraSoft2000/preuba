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
@Table(name="Departamento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idDepartamento;
	 @NotNull
	 private String nombre;
	 private Date fechaRegistro;
	 private Date fechaActualizacion;
	 private String estado;
	 
	
	 @JsonIgnoreProperties(value = {"departamento", "hibernateLazyInitializer", "handler"}  , allowSetters = true)
	 @OneToMany(mappedBy = "departamento" , cascade = CascadeType.ALL  , orphanRemoval = true)
	 private List<Sucursal> sucursales;
	 
	 @JsonIgnoreProperties(value = {"departamento", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	 @OneToMany(mappedBy = "departamento" , cascade = CascadeType.ALL  , orphanRemoval = true)
	 private List<Empresa> empresas;
	 
	public Departamento() {
		this.sucursales= new ArrayList<>();
		this.empresas= new ArrayList<>();
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
	public int getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales.clear();
		sucursales.forEach(p-> this.addDepartamento(p));
	}
	public void addDepartamento(Sucursal sucurales) {
		this.sucursales.add(sucurales);
		sucurales.setDepartamento(this);
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas.clear();
		empresas.forEach(p-> this.addDepartamentoEmpresa(p));
	}
	public void addDepartamentoEmpresa(Empresa empresa) {
		this.empresas.add(empresa);
		empresa.setDepartamento(this);
	}
	
}
