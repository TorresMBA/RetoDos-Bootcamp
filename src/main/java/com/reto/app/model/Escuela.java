package com.reto.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "escuela")
public class Escuela implements Serializable{

	private static final long serialVersionUID = -7079432688868754905L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEscuela;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	private Facultad facultad;
	
	@NotEmpty(message = "Este campo es requerido")
	@NotNull
	private String nombre;
	
	@NotNull(message = "Este campo es requerido")
	private Integer cantAlumnos;
	
	@NotNull(message = "Este campo es requerido")
	private Double recursoFiscal;
	
	private Boolean licenciada;
	
	@NotNull(message = "Este campo es requerido")
	private Integer calificacion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	public Long getIdEscuela() {
		return idEscuela;
	}
	public void setIdEscuela(Long idEscuela) {
		this.idEscuela = idEscuela;
	}
	public Facultad getFacultad() {
		return facultad;
	}
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantAlumnos() {
		return cantAlumnos;
	}
	public void setCantAlumnos(Integer cantAlumnos) {
		this.cantAlumnos = cantAlumnos;
	}
	public Double getRecursoFiscal() {
		return recursoFiscal;
	}
	public void setRecursoFiscal(Double recursoFiscal) {
		this.recursoFiscal = recursoFiscal;
	}
	public Boolean getLicenciada() {
		return licenciada;
	}
	public void setLicenciada(Boolean licenciada) {
		this.licenciada = licenciada;
	}
	public Integer getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	@Override
	public String toString() {
		return "Escuela [idEscuela=" + idEscuela + ", facultad=" + facultad + ", nombre=" + nombre + ", cantAlumnos="
				+ cantAlumnos + ", recursoFiscal=" + recursoFiscal + ", licenciada=" + licenciada + ", calificacion="
				+ calificacion + ", fechaRegistro=" + fechaRegistro + "]";
	}	
}
