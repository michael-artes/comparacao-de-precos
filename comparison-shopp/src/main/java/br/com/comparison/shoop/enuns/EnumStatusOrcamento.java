package br.com.comparison.shoop.enuns;

import org.jboss.weld.exceptions.IllegalArgumentException;

public enum EnumStatusOrcamento {
	
	ABERTO(0, "Status inicial do orçamento"),
	SALVO(1, "Quando o orçamento é salvo pelo usuário");
	
	private int id;
	private String desc;
	
	private EnumStatusOrcamento(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public static EnumStatusOrcamento findById(int id){
		for (EnumStatusOrcamento e : values()) {
			if(id == e.getId()){
				return e;
			}
		}
		
		throw new IllegalArgumentException("ID" + id + " nao encontrado em EnumStatusOrcamento");
	}

	public int getId() {
		return this.id;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
}
