package com.reto.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.reto.app.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByNombreUsuario(String nombreUsuario);
}
