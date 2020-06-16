package com.clases;

import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.clases.codigueras.CodZona;

public class UbicacionesObj {

	private CodZona classZona; 
	private CodDepartamento classDto;
	private CodLocalidad classLoc;
	
	public UbicacionesObj(CodZona classZona, CodDepartamento classDto, CodLocalidad classLoc) {
		this.classZona = classZona;
		this.classDto = classDto;
		this.classLoc = classLoc;
	}
	public UbicacionesObj() {
		this.classZona = null;
		this.classDto = null;
		this.classLoc = null;		
	}
	public CodZona getClassZona() {
		return classZona;
	}
	public void setClassZona(CodZona classZona) {
		this.classZona = classZona;
	}
	public CodDepartamento getClassDto() {
		return classDto;
	}
	public void setClassDto(CodDepartamento classDto) {
		this.classDto = classDto;
	}
	public CodLocalidad getClassLoc() {
		return classLoc;
	}
	public void setClassLoc(CodLocalidad classLoc) {
		this.classLoc = classLoc;
	} 
	
}
