package br.com.comparison.shoop.persistenceTest;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.entity.Empresa;
import br.com.comparison.shoop.enuns.EnumPerfil;

public class PersistenceTest {
	
	public static final String PERSISTENCE_NAME = "PU_POSTGRES";
	
	
	@Test
	public void testConnectionBD(){
		@SuppressWarnings("unused")
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
	}
	
	@Test
	public void testPersistenceUsuario(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		EntityManager em = factory.createEntityManager();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Michael2");
		usuario.setLogin("mmoreira3");
		usuario.setSenha("123456");
		usuario.setPerfil(EnumPerfil.findById(2));
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();
		
		Assert.assertFalse(true);//Se chegou aqui é por que conectou corretamente
	}
	
	@Test
	public void testPersistenceEmpresa(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		EntityManager em = factory.createEntityManager();
		
		Empresa empresa = new Empresa();
		empresa.setAtivo(true);
		empresa.setUsuario(em.getReference(Usuario.class, 8));
		empresa.setCnpj("12.213.654/0001-54");
		empresa.setDataCadastro(new Date());
		empresa.setDescricao("Cadastrando empresa de teste");
		empresa.setNomeFantasia("Empresa Teste 2");
		empresa.setRazaoSocial("Empresa Teste 2 LTDA-ME");
		
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		
		em.close();
		
		Assert.assertFalse(true);//Se chegou aqui é por que conectou corretamente
	}

}
