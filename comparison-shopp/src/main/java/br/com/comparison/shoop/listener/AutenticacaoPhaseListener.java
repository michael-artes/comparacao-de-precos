package br.com.comparison.shoop.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

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
		
		System.out.println("Pegando o id da View " + event.getPhaseId().toString());
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
