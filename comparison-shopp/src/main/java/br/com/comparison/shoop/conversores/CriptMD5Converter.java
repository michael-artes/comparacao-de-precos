package br.com.comparison.shoop.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.comparison.shoop.cript.CriptMD5;

@FacesConverter(value="criptMD5Converter")
public class CriptMD5Converter implements Converter{

	private static final CriptMD5 MD5 = CriptMD5.getInstance();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent ui, String string) {
		
		if (string == null || string.isEmpty()) {
			return null;
		}
		
		return MD5.cript(string);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent ui, Object object) {
		return "";
	}

}
