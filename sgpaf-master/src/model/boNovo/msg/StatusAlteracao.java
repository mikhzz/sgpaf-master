package model.boNovo.msg;

public enum StatusAlteracao implements Mensagem {
	sucesso("Usu�rio alterado com sucesso"), erroCpf("Cpf j� Cadastrado"), erroLoguin(
			"Login j� Utilizado"), erroCadastro("Erro ao cadastrar usu�rio"), erroPerfil("tipo de perfilç nao definido")
	,sucessoUsuarioEmpresa("Empresa alterada com sucesso."), erroCadastroUsuarioEmpresa("Erro ao alterar vinculo empresa.")
	;

	private String mensagem;

	StatusAlteracao(String mensagem){
		this.mensagem=mensagem;
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