package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import interfaces.UsuarioFolhaMestraDAO;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioFolhaMestra;
import model.daoNovo.db.UsuarioProcedimento;

public class UsuarioFolhaMestraDaoImpl extends BaseDaoImpl<UsuarioFolhaMestra, Long> implements UsuarioFolhaMestraDAO, Serializable{

	@Override
	public List<UsuarioFolhaMestra> pesquisarPor(String colunaJava, String consulta, FolhaMestra folhaMestra) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioFolhaMestra.class);
		crit.add(Restrictions.eq(colunaJava, consulta));
		crit.add(Restrictions.eq("folhaMestra", folhaMestra));
		List<UsuarioFolhaMestra> usuarioFolhasMestras = crit.list();
		session.close();
		return usuarioFolhasMestras;
	}

	@Override
	public List<UsuarioFolhaMestra> pesquisarPorUsuario(Usuario usuario) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioFolhaMestra.class);
		crit.add(Restrictions.eq("usuario", usuario));
		List<UsuarioFolhaMestra> usuarioFolhasMestras = crit.list();
		session.close();
		return usuarioFolhasMestras;
	}

	@Override
	public List<UsuarioFolhaMestra> pesquisarPorFolhaMestra(FolhaMestra folhaMestra) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioFolhaMestra.class);
		crit.add(Restrictions.eq("folhaMestra", folhaMestra));
		List<UsuarioFolhaMestra> usuarioFolhasMestras = crit.list();
		session.close();
		return usuarioFolhasMestras;
	}
	
//	public static void main(String[] args) {
//		UsuarioFolhaMestraDaoImpl usrFolhDao = new UsuarioFolhaMestraDaoImpl();
		
//		FolhaMestra folhaMestra = new FolhaMestra();
//		folhaMestra.setIdFolhamestra(142L);
//		
//		List<UsuarioFolhaMestra> usuarioFolhaMestras = usrFolhDao.pesquisarPorFolhaMestra(folhaMestra);
//		
//		for (UsuarioFolhaMestra usuarioFolhaMestra : usuarioFolhaMestras) {
//			System.out.println("Usuario: "+ usuarioFolhaMestra.getUsuario().getNome());
//			System.out.println("CONCLUSAO FOLHA MESTRA: "+ usuarioFolhaMestra.getFolhaMestra().getConclusao());
//			System.out.println("DESCRICAO: "+ usuarioFolhaMestra.getDescricao());
//		}
		
//		Usuario usuario = new Usuario();
//		usuario.setIdUsuario(55L);
//		
//		List<UsuarioFolhaMestra> usuarioFolhaMestras = usrFolhDao.pesquisarPorUsuario(usuario);
//		
//		for (UsuarioFolhaMestra usuarioFolhaMestra : usuarioFolhaMestras) {
//			System.out.println("Usuario: "+ usuarioFolhaMestra.getUsuario().getNome());
//			System.out.println("CONCLUSAO FOLHA MESTRA: "+ usuarioFolhaMestra.getFolhaMestra().getConclusao());
//			System.out.println("DESCRICAO: "+ usuarioFolhaMestra.getDescricao());
//		}
//	}

}
