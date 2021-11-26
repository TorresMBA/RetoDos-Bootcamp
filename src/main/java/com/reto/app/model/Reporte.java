package com.reto.app.model;

import java.io.ByteArrayInputStream;

public class Reporte {
	
	private String fileName;
	
	private ByteArrayInputStream stream;
	
	private Integer length;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ByteArrayInputStream getStream() {
		return stream;
	}

	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
