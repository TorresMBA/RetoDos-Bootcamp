package com.reto.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facultad")
public class Facultad implements Serializable{

	private static final long serialVersionUID = -9143607726935421777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descFacultad;
	private Date fechaRegistro;
	
	public Long getIdFacultad() {
		return id;
	}
	public void setIdFacultad(Long idFacultad) {
		this.id = idFacultad;
	}
	public String getDescFacultad() {
		return descFacultad;
	}
	public void setDescFacultad(String descFacultad) {
		this.descFacultad = descFacultad;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	@Override
	public String toString() {
		return "Facultad [idFacultad=" + id + ", descFacultad=" + descFacultad + ", fechaRegistro="
				+ fechaRegistro + "]";
	}
}
