package com.example.exploraVentas.usuario.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.exploraVentas.usuario.entity.Usuario;
import com.example.exploraVentas.usuario.entity.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	 @Autowired
	    UsuarioService usuarioService;

	    @Override
	    public UserDetails loadUserByUsername(String nombreUsuarioOrEmail) throws UsernameNotFoundException {
	        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuarioOrEmail).get();
	        return UsuarioPrincipal.build(usuario);
	    	
	    	}

}
