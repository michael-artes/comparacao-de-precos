package br.com.comparison.shoop.enuns;

import org.jboss.weld.exceptions.IllegalArgumentException;

public enum EnumPerfil {
	
	ADMINISTRADOR(0, "Perfil do tipo usuario administrador"),
	EMPRESA(1, "Perfil do tipo usuario empresa"),
	COMUM(2, "Perfil do tipo usuario comum");
	
	private int id;
	private String desc;
	
	private EnumPerfil(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public static EnumPerfil findById(int id){
		for (EnumPerfil enumPerfil : values()) {
			if(id == enumPerfil.getId()){
				return enumPerfil;
			}
		}
		
		throw new IllegalArgumentException("ID" + id + " nao encontrado em EnumPerfil");
	}

	public int getId() {
		return this.id;
	}
	
	public String getDesc(){
		return this.desc;
	}

	
}
