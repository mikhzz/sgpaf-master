package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Procedimento;

public interface ProcedimentoDAO extends BaseDAO<Procedimento,Long>{
	List<Procedimento> listarTodos() throws HibernateException; 
	List<Procedimento> listarTodosPor(String colunaBanco, String consulta, Balancete balancete) throws HibernateException;
	List<Procedimento> listarTodosPorBalancete(Balancete pBalancete) throws HibernateException; 
	List<Procedimento> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Balancete pBalancete) throws HibernateException;
}
