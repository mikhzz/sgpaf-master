package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;

public interface LancamentoDAO extends BaseDAO<Lancamento,Long>{
	
	List<Lancamento> consultarTodosLancamentos()throws HibernateException;
	List<Lancamento> consultarTodosBalancete(Balancete pBalancete)throws HibernateException;
	List<Lancamento> consultarPorID(Long pIDLancamento)throws HibernateException;
	List<Lancamento> pesquisarPor(String colunaJava, String consulta,Balancete pBalancete) throws HibernateException;
	List<Lancamento> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Balancete pBalancete) throws HibernateException;
}
