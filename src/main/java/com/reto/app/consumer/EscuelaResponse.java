package com.reto.app.consumer;

import java.util.List;

import com.reto.app.model.Escuela;

public class EscuelaResponse {
	private List<Escuela> escuelas;

	public List<Escuela> getEscuelas() {
		return escuelas;
	}

	public void setEscuelas(List<Escuela> escuelas) {
		this.escuelas = escuelas;
	}

	@Override
	public String toString() {
		return "EscuelaResponse [escuelas=" + escuelas + "]";
	}
	
	
}
