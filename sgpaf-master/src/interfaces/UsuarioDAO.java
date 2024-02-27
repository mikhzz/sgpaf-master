package interfaces;
import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.Usuario;



public interface UsuarioDAO  extends BaseDAO<Usuario, Long>{
	List<Usuario> pesquisarTodos(int ativo, int habilitado) throws HibernateException;
	List<Usuario> pesquisarPor(String colunaJava, String consulta, int ativo, int habilitado) throws HibernateException;
	Usuario pesquisarPorID(Long pID, int ativo, int habilitado )throws HibernateException;
	boolean editarSemSenha(Usuario usr)throws HibernateException;
	Usuario logar(String login,String senha)throws HibernateException;
}
