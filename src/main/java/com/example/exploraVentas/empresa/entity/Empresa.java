package com.example.exploraVentas.empresa.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.exploraVentas.usuario.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Empresa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idEmpresa;
	 @NotNull
	 private String nombreEmpresa;
	 @NotNull
	 private String numeroCelular;
	 private String latitud;
	 private String longitud;
	 @NotNull
	 private String direccion;
	 @NotNull
	 private String descripcion;
	 @NotNull
	 private String nit;
	 @NotNull
	 private String razonSocial;


	 
	 @JsonIgnoreProperties(value = {"empresas", "hibernateLazyInitializer", "handler"} ,allowSetters = true)
	 @ManyToOne()
	 @JoinColumn(name = "idDepartamento")
	 private Departamento departamento;
	 
	 private Date fechaRegistro;
	 private Date fechaActualizacion;
	 private String estado;
	 
	
	 
	 @JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	 @OneToMany(mappedBy = "empresa" , cascade = CascadeType.ALL  , orphanRemoval = true)
	 private List<Sucursal> sucursales;
	 
	 @JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	 @OneToMany(mappedBy = "empresa" , cascade = CascadeType.ALL  , orphanRemoval = true)
	 private List<Usuario> usuarios;
	 
	 
	 @JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	 @OneToMany(mappedBy = "empresa" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
	 private List<ImagenEmpresa> imagenesEmpresa;

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
	public Empresa() {
		this.imagenesEmpresa= new ArrayList<>();
		this.sucursales= new ArrayList<>();
	}


	public int getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getNumeroCelular() {
		return numeroCelular;
	}


	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
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


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getNit() {
		return nit;
	}


	public void setNit(String nit) {
		this.nit = nit;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
		sucursales.forEach(p-> this.addSucursal(p));
	}
	
	public void addSucursal(Sucursal sucurales) {
		this.sucursales.add(sucurales);
		sucurales.setEmpresa(this);
	}

	public List<ImagenEmpresa> getImagenesEmpresa() {
		return imagenesEmpresa;
	}


	public void setImagenesEmpresa(List<ImagenEmpresa> imagenEmpresas) {
		this.imagenesEmpresa.clear();
		imagenEmpresas.forEach(p-> this.addImagen(p));
	}
	public void addImagen(ImagenEmpresa imagenEmpresa) {
		this.imagenesEmpresa.add(imagenEmpresa);
		imagenEmpresa.setEmpresa(this);
	}

	 

}
