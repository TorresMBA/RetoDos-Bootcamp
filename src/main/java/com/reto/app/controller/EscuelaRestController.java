package com.reto.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.app.model.Escuela;
import com.reto.app.response.EscuelaResponseRest;
import com.reto.app.service.IEscuelaService;

@RestController
@RequestMapping("/api")
public class EscuelaRestController {

	@Autowired
	private IEscuelaService escuelaService;
	
	@GetMapping("/escuelas")
	public ResponseEntity<EscuelaResponseRest> listarEscuelas() {
		ResponseEntity<EscuelaResponseRest> response = escuelaService.listarEscuelas();
		return response;
	}
	
	@GetMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> buscarPorId(@PathVariable Long id) {
		ResponseEntity<EscuelaResponseRest> response = escuelaService.buscarEscuelaPorId(id);
		return response;
	}
	
	@PostMapping("/escuelas")
	public ResponseEntity<EscuelaResponseRest> crear(@RequestBody Escuela escuela) {
		ResponseEntity<EscuelaResponseRest> response = escuelaService.crearEscuela(escuela);
		return response;
	}
	
	@PutMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> actualizar (@RequestBody Escuela escuela, @PathVariable Long id) {
		ResponseEntity<EscuelaResponseRest> response = escuelaService.actualizarEscuela(escuela, id);
		return response;
	}
	
	@DeleteMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> eliminar (@PathVariable Long id) {
		ResponseEntity<EscuelaResponseRest> response = escuelaService.eliminarEscuela(id);
		return response;
	}
	
}
