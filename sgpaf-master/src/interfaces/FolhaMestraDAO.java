package interfaces;

import java.util.List;

import org.hibernate.HibernateException;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.FolhaMestra;

public interface FolhaMestraDAO extends BaseDAO<FolhaMestra, Long>{
	
	List<FolhaMestra> pesquisarTodos(int ativo) throws HibernateException;
	List<FolhaMestra> pesquisarPor(String pColunaBanco, String consulta, int ativo) throws HibernateException;
	FolhaMestra pesquisarPorID(Long pID, int pAtivo);
	List<FolhaMestra> consultaComparadorPorColuna(String pColunaBanco,String pComparador,String pConsulta,Balancete pBalancete, int pAtivo) throws HibernateException;
}
