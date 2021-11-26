package com.reto.app.service;

import org.springframework.http.ResponseEntity;

import com.reto.app.model.Escuela;
import com.reto.app.response.EscuelaResponseRest;

public interface IEscuelaService {
	
	public ResponseEntity<EscuelaResponseRest> listarEscuelas();
	
	public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id);
	
	public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela);
	
	public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id);
	
	public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id);
}
