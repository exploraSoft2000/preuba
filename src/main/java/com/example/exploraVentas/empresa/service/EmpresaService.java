package com.example.exploraVentas.empresa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exploraVentas.empresa.entity.Empresa;
import com.example.exploraVentas.empresa.repository.EmpresaRepository;





@Service
@Transactional
public class EmpresaService {
	@Autowired
	EmpresaRepository empresaRepository;
	public Optional<Empresa> getByIdEmpresa(int idEmpresa) {
		return empresaRepository.findById(idEmpresa);
	}
}
