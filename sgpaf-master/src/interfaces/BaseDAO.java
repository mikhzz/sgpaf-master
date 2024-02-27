package interfaces;

import org.hibernate.HibernateException;

public interface BaseDAO <T,ID>{
	
	boolean salvarOuAlterar(T entidade)
            throws HibernateException;

	boolean excluir(T entidade)
            throws HibernateException;
	

}
