package com.example.exploraVentas.usuario.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.exploraVentas.usuario.entity.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	 Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
