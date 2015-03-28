import javax.inject.Named;



@Named
public class Teste {
	
	private String teste;
	
	public String getMensagem(){
		
		teste = "teste 12345";
		
		return "Deu Certo de novo" + teste;
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}
	
}
