package model.daoNovo;

import model.daoNovo.base.AutoDao;
import model.daoNovo.db.Perfil;

public class PerfilDAO extends AutoDao<Perfil,Integer>{

	public PerfilDAO( ) {
		super( Perfil.class);
	}
}
