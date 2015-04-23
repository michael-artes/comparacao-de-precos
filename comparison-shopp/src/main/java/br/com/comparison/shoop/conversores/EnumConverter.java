package br.com.comparison.shoop.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.comparison.shoop.enuns.EnumPerfil;

@FacesConverter(value="enumConverter")
public class EnumConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent ui, String string) {
		
		if ("EMPRESA".equals(string)) {
			return EnumPerfil.EMPRESA;
		}
		
		if ("CONSUMIDOR".equals(string)) {
			return EnumPerfil.CONSUMIDOR;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent ui, Object object) {
		
		if (object != null) {
			
			if (object instanceof EnumPerfil) {
				EnumPerfil e = (EnumPerfil) object;
				return e.toString();
			}
			
		}
		
		return null;
	}

}
