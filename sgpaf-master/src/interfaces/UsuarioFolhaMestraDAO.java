package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioFolhaMestra;

public interface UsuarioFolhaMestraDAO extends BaseDAO<UsuarioFolhaMestra, Long>{
	
	List<UsuarioFolhaMestra> pesquisarPor(String colunaJava, String consulta, FolhaMestra folhaMestra) throws HibernateException;
	List<UsuarioFolhaMestra> pesquisarPorUsuario(Usuario usuario) throws HibernateException;
	List<UsuarioFolhaMestra> pesquisarPorFolhaMestra(FolhaMestra folhaMestra) throws HibernateException;
}
