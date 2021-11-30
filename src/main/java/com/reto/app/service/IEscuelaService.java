package com.reto.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.reto.app.model.Escuela;
import com.reto.app.model.Facultad;
import com.reto.app.model.Reporte;
import com.reto.app.response.EscuelaResponseRest;

import net.sf.jasperreports.engine.JRException;

public interface IEscuelaService {
	
	public ResponseEntity<EscuelaResponseRest> listarEscuelas();
	
	public List<Facultad> listarFacultad();
	
	public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id);
	
	public Reporte obtenerReporteEscuela(Map<String, Object> params) throws JRException, IOException, SQLException;
	
	public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela);
	
	public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id);
	
	public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id);
}
