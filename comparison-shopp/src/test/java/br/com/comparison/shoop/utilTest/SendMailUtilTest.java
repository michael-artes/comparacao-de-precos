package br.com.comparison.shoop.utilTest;

import org.apache.commons.mail.EmailException;
import org.junit.Assert;
import org.junit.Test;

import br.com.comparison.shoop.util.SendMail;

public class SendMailUtilTest {
	
	
	@Test
	public void sendMail() throws EmailException{
		SendMail mail = new SendMail("michael.artes@outlook.com", "Criando usuario", "usuario criado com sucesso");
		mail.sendMail();
		Assert.assertTrue(true);
	}

}
