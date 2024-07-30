package com.example.exploraVentas.usuario.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.exploraVentas.empresa.entity.Empresa;
import com.example.exploraVentas.empresa.entity.ImagenEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
	@NotNull
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String tokenPassword;
    @NotNull
    private String primerApellido;
    private String segundoApellido; 
    @NotNull
    private Date fechaNacimiento;
    @NotNull
    private String numeroCelular;
    @NotNull
    private String ci;
    @NotNull
    private String genero;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String estado;
    private String imagen;
	@JsonIgnoreProperties(value = {"usuarios", "hibernateLazyInitializer", "handler"} , allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    @JsonIgnoreProperties(value = {"usuario", "hibernateLazyInitializer", "handler"} , allowSetters = true)
   	@OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL  , orphanRemoval = true)
   	private List<UsuarioRol> usuarioRol;
    
    @JsonIgnoreProperties(value = {"usuario", "hibernateLazyInitializer", "handler"} , allowSetters = true)
   	@OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL  , orphanRemoval = true)
   	private List<UsuarioPermiso> usuarioPermiso;
    public Usuario() {
    	this.usuarioRol= new ArrayList<>();
    	this.usuarioPermiso= new ArrayList<>();
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
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTokenPassword() {
		return tokenPassword;
	}
	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}
	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol.clear();
		usuarioRol.forEach(p-> this.addRol(p));
	}
	public void addRol(UsuarioRol usuarioRol) {
		this.usuarioRol.add(usuarioRol);
		usuarioRol.setUsuario(this);
	}
	public List<UsuarioPermiso> getUsuarioPermiso() {
		return usuarioPermiso;
	}
	public void setUsuarioPermiso(List<UsuarioPermiso> usuarioPermiso) {
		this.usuarioPermiso.clear();
		usuarioPermiso.forEach(p-> this.addPermiso(p));
	}
	public void addPermiso(UsuarioPermiso usuarioPermiso) {
		this.usuarioPermiso.add(usuarioPermiso);
		usuarioPermiso.setUsuario(this);
	}
	
}
