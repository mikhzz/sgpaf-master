package model.daoNovo;

import model.daoNovo.base.BaseDAO_TablesJoin;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;

public class UsuarioEmpresaJoinDAO extends BaseDAO_TablesJoin<Usuario,UsuarioEmpresa,Empresa,Integer>{

	public UsuarioEmpresaJoinDAO() {
		super(Usuario.class, UsuarioEmpresa.class, Empresa.class);
	}

	
	


}
