package model.daoNovo;

import model.daoNovo.base.AutoDao;
import model.daoNovo.db.Procedimento;

public class ProcedimentoDAO extends AutoDao<Procedimento,Integer>{
	public ProcedimentoDAO( ) {
		super( Procedimento.class);
	}
}
