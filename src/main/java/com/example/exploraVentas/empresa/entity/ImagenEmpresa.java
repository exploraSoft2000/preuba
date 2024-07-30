package com.example.exploraVentas.empresa.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="ImagenEmpresa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImagenEmpresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idImagenEmpresa;
	
	@JsonIgnoreProperties(value = {"imagenesEmpresa", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmpresa")
	 private Empresa empresa;
	@Column(columnDefinition="TEXT")
	@Lob
	 private String imgUrl;
	 private Boolean principal;
	 private Date fechaRegistro;
	 private Date fechaActualizacion;
	 private String estado;

	 
	public ImagenEmpresa() {
		
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
	public int getIdImagenEmpresa() {
		return idImagenEmpresa;
	}
	public void setIdImagenEmpresa(int idImagenEmpresa) {
		this.idImagenEmpresa = idImagenEmpresa;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
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
