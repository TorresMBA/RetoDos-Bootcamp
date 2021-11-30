package com.reto.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reto.app.consumer.MetadataResponse;
import com.reto.app.consumer.Response;
import com.reto.app.model.Escuela;
import com.reto.app.model.Facultad;
import com.reto.app.model.Usuario;

@Service
public class EscuelaConsumerService {
	
	private static final Logger log = LoggerFactory.getLogger(EscuelaConsumerService.class);
	
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Value("${resource.escuelas}")
	 private String resource;
	 
	 @Value("${resource.escuelas}/{id}")
	 private String idResource;
	 
	 public String login(Usuario usuario) {
		 String data = restTemplate.postForObject("http://localhost:8080/api/login", usuario, Usuario.class).toString();
		 return data;
	 }
	 
	 public List<Escuela> listadoEScuelas(){
		 ResponseEntity<Response> exchangEntity = restTemplate.exchange(resource, HttpMethod.GET, null, Response.class);
			Response dto = exchangEntity.getBody();
			log.info(dto.getEscuelaResponse().getEscuelas().toString());
			return dto.getEscuelaResponse().getEscuelas();
	 }
	 
	 public List<Facultad> listadoFacultades(){
		 return Arrays.stream(restTemplate.getForObject(resource+"/facultades", Facultad[].class)).collect(Collectors.toList());
	 }
	 
	 public List<Escuela> buscarPorId(Long id){
		 ResponseEntity<Response> exchangEntity = restTemplate.exchange(idResource, HttpMethod.GET, null, Response.class, id);
			Response dto = exchangEntity.getBody();
			//log.info(dto.getEscuelaResponse().getEscuelas().toString());
			return dto.getEscuelaResponse().getEscuelas();
	 }
	 
	 public void agregarEscuela(Escuela escuela) {
		//restTemplate.exchange(resource, HttpMethod.POST, new HttpEntity<>(dto), EscuelaDto.class).getBody();
		restTemplate.postForObject(resource, escuela, Escuela.class);
	 }
	 
	 public void actualizarEscuela(Long id, Escuela escuela) {
		 restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(escuela), Escuela.class, id).getBody();
	 }
	 
	 public MetadataResponse[] eliminarEscuela(Long id) {
		ResponseEntity<Response> entity = restTemplate.exchange(idResource, HttpMethod.DELETE, null, Response.class, id);
		Response response = entity.getBody();
		return response.getMetadata();
	 }
}
