package com.example.exploraVentas.usuario.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Permiso")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permiso {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermiso;
	@NotNull
    private String nombre;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String estado;
    @JsonIgnoreProperties(value = {"permiso", "hibernateLazyInitializer", "handler"} , allowSetters = true)
   	@OneToMany(mappedBy = "permiso" , cascade = CascadeType.ALL  , orphanRemoval = true)
   	private List<RolPermiso> rolPermiso;
    
    @JsonIgnoreProperties(value = {"permiso", "hibernateLazyInitializer", "handler"} , allowSetters = true)
   	@OneToMany(mappedBy = "permiso" , cascade = CascadeType.ALL  , orphanRemoval = true)
   	private List<UsuarioPermiso> usuarioPermiso;
    
    
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
    
    
	public Permiso() {
		this.rolPermiso= new ArrayList<>();
		this.usuarioPermiso= new ArrayList<>();
	}

	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
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
	public List<RolPermiso> getRolPermiso() {
		return rolPermiso;
	}
	public void setRolPermiso(List<RolPermiso> rolPermiso) {
		this.rolPermiso.clear();
		rolPermiso.forEach(p-> this.addRol(p));
	}
	public void addRol(RolPermiso rolPermiso) {
		this.rolPermiso.add(rolPermiso);
		rolPermiso.setPermiso(this);
	}

	public List<UsuarioPermiso> getUsuarioPermiso() {
		return usuarioPermiso;
	}

	public void setUsuarioPermiso(List<UsuarioPermiso> usuarioPermiso) {
		this.usuarioPermiso.clear();
		usuarioPermiso.forEach(p-> this.addUsuario(p));
	}
	public void addUsuario(UsuarioPermiso usuarioPermiso) {
		this.usuarioPermiso.add(usuarioPermiso);
		usuarioPermiso.setPermiso(this);
	}
    
}
