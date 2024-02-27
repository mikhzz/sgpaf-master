package interfaces;


import org.hibernate.HibernateException;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Materialidade;

public interface MaterialidadeDAO extends BaseDAO<Materialidade, Long>{
	
	Materialidade pesquisarPorBalancete(Balancete pBalancete) throws HibernateException;

}
