package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Empresa;

public interface EmpresaDAO extends BaseDAO<Empresa, Long> {
	List<Empresa> pesquisarTodos(int ativo) throws HibernateException;

	List<Empresa> pesquisarPor(String pColunaJava, String consulta, int ativo) throws HibernateException;

	Empresa pesquisarPorID(Long pID, int ativo) throws HibernateException;
	List<Empresa> consultarTipo()throws HibernateException;
}
