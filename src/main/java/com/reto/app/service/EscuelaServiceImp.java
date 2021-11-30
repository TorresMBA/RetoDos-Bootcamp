package com.reto.app.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reto.app.jasper.JasperReportManager;
import com.reto.app.model.Escuela;
import com.reto.app.model.Facultad;
import com.reto.app.model.Reporte;
import com.reto.app.model.dao.IEscuelaDao;
import com.reto.app.model.dao.IFacultadDao;
import com.reto.app.response.EscuelaResponseRest;

import net.sf.jasperreports.engine.JRException;

@Service
public class EscuelaServiceImp implements IEscuelaService{
	
	@Autowired
	private IEscuelaDao escuelaDao;
	
	@Autowired
	private IFacultadDao facultadDao;
	
	@Autowired
	private JasperReportManager reportManager;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EscuelaResponseRest> listarEscuelas() {
		EscuelaResponseRest response = new EscuelaResponseRest();
		
		try {
			List<Escuela> escuela = (List<Escuela>) escuelaDao.findAll();
			
			response.getEscuelaResponse().setEscuelas(escuela);
			response.setMetadata("Respuesta ok", "1", "Respuesta Exitosa");
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "Error al consultar los libros");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EscuelaResponseRest> buscarEscuelaPorId(Long id) {
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		
		try {
			Optional<Escuela> escuela = escuelaDao.findById(id);
			if(escuela.isPresent()) {
				list.add(escuela.get());
				response.getEscuelaResponse().setEscuelas(list);
			} else {
				response.setMetadata("Respuesta failed", "-1", "Escuela no encontrada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "Error a consultar el libro especificado");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.setMetadata("Respuesta ok", "1", "Respuesta exitosa");
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EscuelaResponseRest> crearEscuela(Escuela escuela) {
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		
		try {
			Escuela escuelaGuardada = escuelaDao.save(escuela);
			
			if(escuelaGuardada != null) {
				list.add(escuelaGuardada);
				response.getEscuelaResponse().setEscuelas(list);
			}else {
				response.setMetadata("Respuesta failed", "-1", "Escuela no guardada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo guardar el libro");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.setMetadata("Respuesta ok", "1", "Escuela creada Correctamente");
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EscuelaResponseRest> actualizarEscuela(Escuela escuela, Long id) {
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		
		try {
			Optional<Escuela> escuelaBuscada = escuelaDao.findById(id);
			
			if(escuelaBuscada.isPresent()) {
				escuelaBuscada.get().setFacultad(escuela.getFacultad());
				escuelaBuscada.get().setNombre(escuela.getNombre());
				escuelaBuscada.get().setCantAlumnos(escuela.getCantAlumnos());
				escuelaBuscada.get().setRecursoFiscal(escuela.getRecursoFiscal());
				escuelaBuscada.get().setLicenciada(escuela.getLicenciada());
				escuelaBuscada.get().setCalificacion(escuela.getCalificacion());
				
				Escuela escuelaActualizar = escuelaDao.save(escuelaBuscada.get());
				
				if(escuelaActualizar != null) {
					response.setMetadata("Respuesta ok", "1", "Escuela Actualizada");
					list.add(escuelaActualizar);
					response.getEscuelaResponse().setEscuelas(list);
				} else {
					response.setMetadata("Respuesta failed", "-1", "Escuela no Actualizada");
					return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				response.setMetadata("Respuesta nok", "-1", "No existe el libro a actualizar");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo actualizar el libro");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EscuelaResponseRest> eliminarEscuela(Long id) {
		
		EscuelaResponseRest response = new EscuelaResponseRest();
		
		try {
			escuelaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "1", "Libro ELiminado");
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta failed", "-1", "No se pudo eliminar el libro");
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public Reporte obtenerReporteEscuela(Map<String, Object> params) throws JRException, IOException, SQLException {
		String filename= "reporte_escuelas";
		Reporte report = new Reporte();
		report.setFileName(filename + ".pdf");
		
		ByteArrayOutputStream stream = reportManager.export(filename, params, dataSource.getConnection());
		
		byte[] bs = stream.toByteArray();		
		report.setStream(new ByteArrayInputStream(bs));
		report.setLength(bs.length);
		
		return report;
	}

	@Override
	public List<Facultad> listarFacultad() {
		return (List<Facultad>) facultadDao.findAll();
	}	
	
}
