$(document).ready(function(){
	
	$("#cadastroNovoUsuario").click(function(){
		$("#formLogin").hide("slow");
		$("#formCadastroUser").show("slow");
	});
	
	$("#cancelarCadastro").click(function(){
		$("#formLogin").show("slow");
		$("#formCadastroUser").hide("slow");
	});
	
});