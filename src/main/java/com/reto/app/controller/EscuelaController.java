	package com.reto.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reto.app.model.Escuela;
import com.reto.app.model.Usuario;
import com.reto.app.service.EscuelaConsumerService;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/escuelas")
@ApiIgnore
public class EscuelaController {
	
	private static final Logger log = LoggerFactory.getLogger(EscuelaController.class);
	
	@Autowired
	private EscuelaConsumerService consumerService;
	
	@GetMapping("/login")
	public String formLogin() {
		return "formLogin";
	}
	
	@PostMapping("/login")
	public String login(Usuario usuario) {
		log.info("Login");
		log.info("Login: " + usuario.toString());
		return consumerService.login(usuario);
	}
	
	@GetMapping
	public String listadoEscuelas(Model model) {
		log.info("Listado Escuelas");
		model.addAttribute("escuelas", consumerService.listadoEScuelas());
		return "index";
	}
		
	@GetMapping("/{id}")
	public String buscarPorEscuela(@PathVariable Long id, Model model) {
		model.addAttribute("escuelas", consumerService.buscarPorId(id));
		return "index";
	}
	
	@GetMapping("/crear")
	public String crear(@ModelAttribute Escuela escuela, BindingResult result, Model model) {
		log.info("Mostrando Form Crear");
		if(result.hasErrors()) {
			return "formEscuela";
		}
		model.addAttribute("facultades", consumerService.listadoFacultades());
		log.info(consumerService.listadoFacultades().toString());
		return "formEscuela";
	}
	
	@PostMapping("/guardar")
	public String guardarEscuela(@ModelAttribute Escuela escuela, BindingResult bindResult, Model model) {
		log.info("Guardar Escuela");
		if(bindResult.hasErrors()) {
			return "formEscuelaGuardar";
		}
		model.addAttribute("facultades", consumerService.listadoFacultades());
		
		log.info("Crear: " + escuela.toString());
		consumerService.agregarEscuela(escuela);
		return "redirect:/escuelas";
	}
	
	@GetMapping("/editar/{id}")
	public String editarEscuela(@PathVariable Long id, @ModelAttribute Escuela escuela, Model model) {
		log.info("Mostrando Form Editar");
		List<Escuela> list = consumerService.buscarPorId(id);
		model.addAttribute("facultades", consumerService.listadoFacultades());
		log.info(consumerService.listadoFacultades().toString());
		model.addAttribute("escuela", list.get(0));
		model.addAttribute("action", "editar");
		return "formEscuela";
	}
	
	@PostMapping("/actualizar")
	public String actualizarEscuela(@ModelAttribute Escuela escuela, BindingResult bindingResult) {
		log.info("Actualizar Escuela");
		if(bindingResult.hasErrors()) {
			return "formEscuela";
		}
		consumerService.actualizarEscuela(escuela.getIdEscuela(), escuela);
		log.info("Actualizar: " + escuela.toString());
		return "redirect:/escuelas";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEscuela(@PathVariable Long id) {
		consumerService.eliminarEscuela(id);
		return "redirect:/escuelas";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
	}
}
