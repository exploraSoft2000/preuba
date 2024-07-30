package com.example.exploraVentas.empresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exploraVentas.empresa.entity.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
}
