package com.nantia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nantia.model.Cliente;
import com.nantia.model.EnvasesTipos;
import com.nantia.repo.ClienteRepository;
import com.nantia.repo.EnvasesTipoRepository;

@Service
public class EnvasesTipoService implements IEnvasesTipoService{

	
	@Autowired
	private EnvasesTipoRepository envasesTipoRepository;
	
	 EnvasesTipos findByDescripcion(String descripcion) {
		EnvasesTipos env = envasesTipoRepository.findByDescripcion(descripcion);
		return env;
	}
	
	@Override
	public List<EnvasesTipos> getAllEnvasesTipo() {
		List<EnvasesTipos> list = new ArrayList<>();	
		envasesTipoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public EnvasesTipos getEnvasesTipoById(long envasesTiposId) {
		EnvasesTipos envase = envasesTipoRepository.findOne(envasesTiposId);
		return envase;
	}
	
	@Override
	public EnvasesTipos getEnvasesTipoByDescripcion(String descripcion) {
		EnvasesTipos envase = findByDescripcion(descripcion);
		return envase;
	}

	@Override
	public EnvasesTipos addEnvasesTipo(EnvasesTipos envasesTipos) {
		return envasesTipoRepository.save(envasesTipos);
	}

	@Override
	public EnvasesTipos updateEnvasesTipo(EnvasesTipos envasesTipos) {
		return envasesTipoRepository.save(envasesTipos);
	}

	@Override
	public void deleteEnvasesTipo(long envasesTiposId) {
		envasesTipoRepository.delete(getEnvasesTipoById(envasesTiposId));
		
	}

	@Override
	public boolean existe(EnvasesTipos envasesTipos) {
		return findByDescripcion(envasesTipos.getDescripcion()) != null;
	}

}
