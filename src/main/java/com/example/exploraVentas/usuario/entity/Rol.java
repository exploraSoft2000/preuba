package com.example.exploraVentas.usuario.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.exploraVentas.empresa.entity.Sucursal;
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
@Table(name = "Rol")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rol {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
	@NotNull
    private String nombre;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String estado;
    @JsonIgnoreProperties(value = {"rol", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	@OneToMany(mappedBy = "rol" , cascade = CascadeType.ALL  , orphanRemoval = true)
	private List<UsuarioRol> usuarioRol;
    @JsonIgnoreProperties(value = {"rol", "hibernateLazyInitializer", "handler"} , allowSetters = true)
   	@OneToMany(mappedBy = "rol" , cascade = CascadeType.ALL  , orphanRemoval = true)
   	private List<RolPermiso> rolPermiso;
    
    
    public Rol() {
    	this.usuarioRol= new ArrayList<>();
    	this.rolPermiso= new ArrayList<>();
	}

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

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
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

	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol.clear();
		usuarioRol.forEach(p-> this.addUsuario(p));
	}
	public void addUsuario(UsuarioRol usuarioRol) {
		this.usuarioRol.add(usuarioRol);
		usuarioRol.setRol(this);
	}

	public List<RolPermiso> getRolPermiso() {
		return rolPermiso;
	}

	public void setRolPermiso(List<RolPermiso> rolPermiso) {
		this.rolPermiso.clear();
		rolPermiso.forEach(p-> this.addPermiso(p));
	}
	public void addPermiso(RolPermiso rolPermiso) {
		this.rolPermiso.add(rolPermiso);
		rolPermiso.setRol(this);
	}
	
}
