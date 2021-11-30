package com.reto.app.service;

import java.util.List;

import com.reto.app.model.Role;
import com.reto.app.model.Usuario;

public interface IUsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario);
	
	public Role guardarRole(Role role);
	
	public void agregarRoleAUsuario(String nombreUsuario, String roleName);
	
	public Usuario getUsuario(String nombreUsuario);
	
	public List<Usuario> listadoUsuario();
}
