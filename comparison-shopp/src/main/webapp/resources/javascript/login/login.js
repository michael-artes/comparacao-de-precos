$(document).ready(function(){
	
	$("#cadastroNovoUsuario").click(function(){
		$("#formLogin").hide("slow");
		$("#formCadastroUser").show("slow");
	});
	
});

function telaLogin(){
	$("#formLogin").show("slow");
	$("#formCadastroUser").hide("slow");
}