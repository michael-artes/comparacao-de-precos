package br.com.comparison.shoop.cript;

public class CriptMD5 {
	
	private static String COMMON_KEY_PASS = "_defaultKeyPass";
	
	private static CriptMD5 criptMD5;
	
	private DesEncrypter encrypter;
	
	public static CriptMD5 getInstance(){
		
		if (criptMD5 == null) {
			criptMD5 = new CriptMD5();
			criptMD5.setPassPhrase(COMMON_KEY_PASS);
		}
		
		return criptMD5;
		
	}
	
	public void setPassPhrase(String newPassPhrase) {
		encrypter = new DesEncrypter( newPassPhrase );
	}
	
	public String cript(Integer n){
		return n == null ? null : cript(n.toString());
	}
	
	public String cript(String v){
		
		if(v == null) return null;
		
		return encrypter.encrypt(v);
	}
	
	public String decript(String v) throws IllegalArgumentException {
		
		if(v == null) return null;
		if(v.isEmpty()) return v;
		
		v += "R"; // colocar mais um caracter que nao influencia, porem inibe o cara de colocar mais um na string original
		
		String decrypted = encrypter.decrypt(v);
		if(decrypted == null || decrypted.isEmpty()) throw new IllegalArgumentException(v);
		
		return decrypted;
	}
	
	public Integer decriptToInt(String v){
		
		if(v == null) return null;
		
		return new Integer( encrypter.decrypt(v) );
	}	
	
	

}
