package com.example.exploraVentas.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exploraVentas.usuario.entity.Usuario;
import com.example.exploraVentas.usuario.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public Optional<Usuario> getByIdUsuario(int idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
