package br.com.comparison.shoop.criptTest;

import org.junit.Assert;
import org.junit.Test;

import br.com.comparison.shoop.cript.CriptMD5;

public class CriptMD5Test {
	
	private static final String criptMD5String = "criptMD5";
	private static final Integer criptMD5Integer = 123456;
	
	private static final CriptMD5 MD5 = CriptMD5.getInstance();
	
	
	
	@Test
	public void testCript(){
		Assert.assertEquals("43VvCOU+DIi5ZXDcyKopww==", MD5.cript(criptMD5String));
		Assert.assertEquals("Kaay7IHdFlQ=", MD5.cript(criptMD5Integer));
	}
	
	@Test
	public void testDecript(){
		Assert.assertEquals(criptMD5String, MD5.decript("43VvCOU+DIi5ZXDcyKopww=="));
		Assert.assertEquals(criptMD5Integer, MD5.decriptToInt("Kaay7IHdFlQ="));
	}

}
