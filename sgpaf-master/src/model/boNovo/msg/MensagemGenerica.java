package model.boNovo.msg;

public class MensagemGenerica implements Mensagem{
	private String mensagem;
	private int Status;
	private boolean sucess;



public MensagemGenerica(String mensagem, int status, boolean sucess) {
		super();
		this.mensagem = mensagem;
		Status = status;
		this.sucess = sucess;
	}
@Override
public String getMensagem() {
	// TODO Auto-generated method stub
	return mensagem;
}
@Override
public int getStatus() {
	// TODO Auto-generated method stub
	return Status;
}
@Override
public boolean getSucess() {
	// TODO Auto-generated method stub
	return sucess;
}
}
