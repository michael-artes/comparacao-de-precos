package br.com.comparison.shoop.interceptadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.comparison.shoop.annotations.Transacao;

@Interceptor
@Transacao
public class TransacaoInterceptor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception{
		
		EntityTransaction txt = entityManager.getTransaction();
		boolean criador = false;
		
		try {
			
			if (!txt.isActive()) {
				
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo
				// operações sem transação)
				txt.begin();
				txt.rollback();
				
				// agora sim inicia a transação
				txt.begin();
				
				
				criador = true;
				
			}
			
			return context.proceed();	
			
		} catch (Exception e) {
			
			if (txt != null && criador) {
				txt.rollback();
			}
			
			throw e;
		} finally{
			
			if (txt != null && txt.isActive() && criador) {
				txt.commit();
			}
			
		}
		
	}	
}
