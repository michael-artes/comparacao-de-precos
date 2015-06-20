package br.com.comparison.shoop.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.comparison.shoop.util.FacesUtil;

public class AutenticacaoPhaseListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1889849610258814253L;
	

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		if (session != null) {
			
			String mensagem = (String) session.getAttribute("msg");
			
			if (mensagem  != null) {
				
				FacesUtil.addInfoMessage(mensagem);
				
				session.setAttribute("msg", null);
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
