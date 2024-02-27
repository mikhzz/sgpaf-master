package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;

import interfaces.BaseDAO;

public class BaseDaoImpl <T,ID> implements BaseDAO<T, ID>{

	private Transaction transacao;
	
	
	@Override
	public boolean salvarOuAlterar(T entidade) throws HibernateException {
		try {
			Session session = HibernateUtil.abreConexao();
			transacao = session.beginTransaction();
			session.saveOrUpdate(entidade);
			
			transacao.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean excluir(T entidade) throws HibernateException {
		try {
			Session session = HibernateUtil.abreConexao();
			transacao = session.beginTransaction();
	        session.delete(entidade);
	        transacao.commit();
	        session.close();
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
