package com.reto.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.reto.app.model.Role;

public interface IRolDao extends CrudRepository<Role, Long>{
	
	public Role findByNombre(String nombre);
}
