package com.nantia.service;

import java.util.List;

import com.nantia.model.EnvasesTipos;

public interface IEnvasesTipoService {
	
	List<EnvasesTipos> getAllEnvasesTipo();
	EnvasesTipos getEnvasesTipoById(long envasesTiposId);
	EnvasesTipos addEnvasesTipo(EnvasesTipos envasesTipos);
	EnvasesTipos updateEnvasesTipo(EnvasesTipos envasesTipos);
	void deleteEnvasesTipo(long envasesTiposId);
	boolean existe(EnvasesTipos envasesTipos);

}
