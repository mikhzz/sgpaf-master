package model.boNovo.msg;

public enum StatusCadastro implements Mensagem {
	sucesso("Usu�rio cadastrado com sucesso"), erroCpf("Cpf j� Cadastrado"), erroLoguin(
			"Login j� Utilizado"), erroCadastro("Erro ao cadastrar usu�rio"), erroPerfil("tipo de perfilç nao definido")
	,erroCadastroEmpresaVinculada("Empresa j� Vinculada ao Usu�rio"), erroEmpresa("Empresa n�o definida."), 
	erroUsuario("Usu�rio n�o definido."), sucessoUsuarioEmpresa("Empresa vinculada com sucesso."), erroCadastroUsuarioEmpresa("Erro ao vincular empresa.");

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
