package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioProcedimento;

public interface UsuarioProcedimentoDAO extends BaseDAO<UsuarioProcedimento,Long> {
	
	List<UsuarioProcedimento> pesquisarPor(String colunaJava, String consulta, Procedimento procedimento) throws HibernateException;
	List<UsuarioProcedimento> pesquisarPorUsuario(Usuario usuario) throws HibernateException;
	List<UsuarioProcedimento> pesquisarPorProcedimento(Procedimento procedimento) throws HibernateException;
	
}
