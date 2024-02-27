package model.boNovo.msg;

public enum StatusCadastro implements Mensagem {
	sucesso("Usuï¿½rio cadastrado com sucesso"), erroCpf("Cpf jï¿½ Cadastrado"), erroLoguin(
			"Login jï¿½ Utilizado"), erroCadastro("Erro ao cadastrar usuï¿½rio"), erroPerfil("tipo de perfilÃ§ nao definido")
	,erroCadastroEmpresaVinculada("Empresa já Vinculada ao Usuário"), erroEmpresa("Empresa não definida."), 
	erroUsuario("Usuário não definido."), sucessoUsuarioEmpresa("Empresa vinculada com sucesso."), erroCadastroUsuarioEmpresa("Erro ao vincular empresa.");

	private String mensagem;

	StatusCadastro(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String getMensagem() {
		// TODO Auto-generated method stub
		return mensagem;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return this.ordinal();
	}

	@Override
	public boolean getSucess() {
		// TODO Auto-generated method stub
		return this.equals(sucesso);
	}
}
