package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;

public interface BalanceteDAO extends BaseDAO<Balancete, Long>{
	
	List<Balancete> pesquisarTodos(int ativo)throws HibernateException;
	List<Balancete> pesquisarPor(String pColunaBanco, String pConsulta, int ativo) throws HibernateException;
	List<Balancete> consultaComparadorPorColuna(String pColunaBanco,String pComparador,String pConsulta,Empresa pEmpresa, int pAtivo) throws HibernateException;
	Balancete pesquisarPorID(Long pID, int pAtivo) throws HibernateException;
}