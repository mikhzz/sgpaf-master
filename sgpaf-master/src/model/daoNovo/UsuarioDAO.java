package model.daoNovo;

import model.daoNovo.base.AutoDao;
import model.daoNovo.db.Usuario;

public class UsuarioDAO extends AutoDao<Usuario,Integer>{
	public UsuarioDAO() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	}
}
