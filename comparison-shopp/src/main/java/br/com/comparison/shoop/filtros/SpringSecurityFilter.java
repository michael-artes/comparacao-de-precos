package br.com.comparison.shoop.filtros;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.comparison.shoop.cript.CriptMD5;
import br.com.comparison.shoop.entity.Usuario;
import br.com.comparison.shoop.managedBeans.UsuarioMB;
import br.com.comparison.shoop.service.EmpresaService;
import br.com.comparison.shoop.service.UsuarioService;
import br.com.comparison.shoop.util.CDIServiceLocator;

public class SpringSecurityFilter extends UsernamePasswordAuthenticationFilter {
	
	private String mensagem;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,	HttpServletResponse response) throws AuthenticationException {
		
		String login = request.getParameter("j_login");
		String senha = request.getParameter("j_password");
		
		try {

			UsuarioService usuarioService = CDIServiceLocator.getBean(UsuarioService.class);
			Usuario usuario = usuarioService.existeUsuario(login, CriptMD5.getInstance().cript(senha));

			if (usuario != null) {
				
				List<GrantedAuthority> createAuthorityList = AuthorityUtils.createAuthorityList(usuario.getPerfil().toString(), "USER");
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(login, senha, createAuthorityList);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				preencherUserSession(login, usuario);
				
				mensagem = "Seja Bem Vindo " + usuario.getLogin();
				
				return authentication;
				
			} else {
				mensagem = "Dados incorretos, favor verifique o seu login e senha";
				throw new BadCredentialsException(mensagem);
			}
			
		} catch (Exception e) {
			mensagem = "Não Foi possível logar no sistema";
			throw new BadCredentialsException(mensagem, e);
		}
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)	throws IOException, ServletException {
		request.getSession().setAttribute("msg", mensagem);
		response.sendRedirect(request.getContextPath() + "/login.xhtml");
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,	HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		request.getSession().setAttribute("msg", mensagem);
		response.sendRedirect(request.getContextPath() + "/pags/home/home.xhtml");
	}
	
	
	private void preencherUserSession(String login, Usuario usuario){
		
		UsuarioMB usuarioMB = CDIServiceLocator.getBean(UsuarioMB.class);
		EmpresaService empresaService = CDIServiceLocator.getBean(EmpresaService.class);
		
		usuarioMB.getUserSession().setNome(login);
		usuarioMB.getUserSession().setLogado(true);
		usuarioMB.getUserSession().setDataAcesso(new Date());
		usuarioMB.getUserSession().setIdUser(usuario.getId());
		usuarioMB.getUserSession().setEnumPerfil(usuario.getPerfil());
		usuarioMB.getUserSession().setEmpresa( empresaService.findEmpresaByIdUser( usuarioMB.getUserSession().getIdUser() ) );
	}
		
}
