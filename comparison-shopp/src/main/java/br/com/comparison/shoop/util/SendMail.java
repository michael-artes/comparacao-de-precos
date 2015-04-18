package br.com.comparison.shoop.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
	final static String username = "comparison.shopp@gmail.com";
	final static String senha = "C0mp4r1s0n-sh0pp";
	
	private static String from;
	private String to; //Para
	private String assunto;
	private String mensagem;
	
	
	public SendMail(String to, String assunto, String mensagem) {
		this.to = to;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}
	
	
	static {
		from = "comparison.shopp@gmail.com"; //DE
	}
	
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public void sendMail(){
		
		Properties props = new Properties();
		
		//GMail via SSL
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		
		Session session = Session.getInstance(props, 
				
				new Authenticator() {
			
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(username, senha);
					}
			
				});
		
		
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom( new InternetAddress(from) );
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.assunto);
			message.setText(this.mensagem);
			
			Transport.send(message);
			
			System.out.println("Email enviado");
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
