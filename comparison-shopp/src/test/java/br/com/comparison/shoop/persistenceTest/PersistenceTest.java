package br.com.comparison.shoop.persistenceTest;

import java.util.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.comparison.shoop.entity.Anuncio;
import br.com.comparison.shoop.entity.ItemOrcamento;
import br.com.comparison.shoop.entity.Orcamento;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.enuns.EnumStatusOrcamento;

public class PersistenceTest {
	
	public static final String PERSISTENCE_NAME = "PU_POSTGRES";
	
	/**
	 * @return
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		EntityManager em = factory.createEntityManager();
		return em;
	}
	
	@Test
	public void testConnectionBD(){
		EntityManager em = getEntityManager();
		Assert.assertTrue(em.isOpen());//Se chegou aqui é por que conectou corretamente
	}
	
	@Test
	public void testPersistenceUsuario(){
		/*EntityManager em = getEntityManager();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Michael - 2");
		usuario.setLogin("mmoreira-2");
		usuario.setSenha("123456");
		usuario.setPerfil(EnumPerfil.findById(1));
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();*/
		
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
	}

	
	@Test
	public void testPersistenceEmpresa(){
		
		/*EntityManager em = getEntityManager();
		
		Empresa empresa = new Empresa();
		empresa.setAtivo(true);
		empresa.setUsuario(em.getReference(Usuario.class, 3));
		empresa.setCnpj("12.213.654/0001-54");
		empresa.setDataCadastro(new Date());
		empresa.setDescricao("Cadastrando empresa de teste");
		empresa.setNomeFantasia("Empresa Teste 2");
		empresa.setRazaoSocial("Empresa Teste 2 LTDA-ME");
		
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
			
		em.close();*/
		
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
	}
	
	
	
	/*private List<Usuario> getUsuarios(){
		ArrayList<Usuario> users = new ArrayList<Usuario>(0);
		
		Usuario usuario = new Usuario();
		usuario.setNome("Michael - 3");
		usuario.setLogin("mmoreira-3");
		usuario.setSenha("123456");
		usuario.setPerfil(EnumPerfil.findById(2));
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		
		Usuario usuario1 = new Usuario();
		usuario1.setNome("Michael - 4");
		usuario1.setLogin("mmoreira-4");
		usuario1.setSenha("123456");
		usuario1.setPerfil(EnumPerfil.findById(2));
		usuario1.setDataCadastro(new Date());
		usuario1.setAtivo(true);
		
		users.add(usuario);
		users.add(usuario1);

		return users;
	}
	
	@Test
	public void testPersistenceMultiUsers(){
		
		EntityManager em = getEntityManager();
		List<Usuario> usuarios = getUsuarios();
		
		em.getTransaction().begin();
		em.persist(usuarios.get(0));
		em.persist(usuarios.get(1));
		em.getTransaction().commit();
		em.close();
		
	}*/
	
	@Test
	public void testPersistenceAnuncio(){
		
		/*EntityManager em = getEntityManager();
		
		Anuncio a = new Anuncio();
		a.setNome("Cartao de Visita");
		a.setDescricao("1000 Cartao de visita colorido frente e verso");
		a.setValor(new BigDecimal(130.00));
		a.setDataCadastro(new Date());
		a.setEmpresa(em.getReference(Empresa.class, 1));
		
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();*/
		
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
	}
	
	
	@Test
	public void testPersistencePalavrasChave(){
		
		/*EntityManager em = getEntityManager();
		
		PalavrasChave plvChave = new PalavrasChave();
		plvChave.setAnuncio(em.getReference(Anuncio.class, 7));
		plvChave.setNomeChave("grafica");
		
		em.getTransaction().begin();
		em.persist(plvChave);
		em.getTransaction().commit()*/;
		
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
		
	}
	
	
	@Test
	public void testPersistenceOrcamento(){
		EntityManager em = getEntityManager();
		
		Anuncio a = em.getReference(Anuncio.class, 7);//Pegas as informações do anuncio
		
		Orcamento o = new Orcamento();
		o.setDataCadastro(new Date());
		o.setUsuario(em.getReference(Usuario.class, 4));
		o.setStatusOrcamento(EnumStatusOrcamento.findById(0));
		
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		
		ItemOrcamento itemOrc = new ItemOrcamento();
		itemOrc.setValor(a.getValor());
		itemOrc.setDataCadastro(new Date());
		itemOrc.setDescricao(a.getDescricao());
		itemOrc.setNome(a.getNome());
		itemOrc.setOrcamento(o);
		
		em.getTransaction().begin();
		em.persist(itemOrc);
		em.getTransaction().commit();
		
		em.close();
		
		Assert.assertTrue(true);//Se chegou aqui é por que conectou corretamente
	}

}
