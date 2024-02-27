package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import interfaces.UsuarioProcedimentoDAO;
import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import model.daoNovo.db.UsuarioProcedimento;

public class UsuarioProcedimentoDaoImpl extends BaseDaoImpl<UsuarioProcedimento, Long> implements UsuarioProcedimentoDAO, Serializable{


	@Override
	public List<UsuarioProcedimento> pesquisarPor(String colunaJava, String consulta, Procedimento procedimento) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioProcedimento.class);
		crit.add(Restrictions.eq(colunaJava, consulta));
		crit.add(Restrictions.eq("procedimento", procedimento));
		List<UsuarioProcedimento> usuarioProcedimentos = crit.list();
		session.close();
		return usuarioProcedimentos;
	}

	@Override
	public List<UsuarioProcedimento> pesquisarPorUsuario(Usuario usuario) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioProcedimento.class);
		crit.add(Restrictions.eq("usuario", usuario));
		List<UsuarioProcedimento> usuarioProcedimentos = crit.list();
		session.close();
		return usuarioProcedimentos;
	}

	@Override
	public List<UsuarioProcedimento> pesquisarPorProcedimento(Procedimento procedimento) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(UsuarioProcedimento.class);
		crit.add(Restrictions.eq("procedimento", procedimento));
		List<UsuarioProcedimento> usuarioProcedimentos = crit.list();
		session.close();
		return usuarioProcedimentos;
	}
//	public static void main(String[] args) {
//		UsuarioProcedimentoDaoImpl usuProcDao = new UsuarioProcedimentoDaoImpl();
		
		// ======================================= PESQUISA POR PROCEDIMENTO
		
//		Procedimento procedimento = new Procedimento();
//		procedimento.setIdProcedimento(132L);
//		
//		List<UsuarioProcedimento> usuarioProcedimentosPorProcedimentos =  usuProcDao.pesquisarPorProcedimento(procedimento);
//		
//		for (UsuarioProcedimento usuarioProcedimento : usuarioProcedimentosPorProcedimentos) {
//			
//			System.out.println("USUARIO: "+usuarioProcedimento.getUsuario().getNome());
//			System.out.println("DESCRICAO: "+usuarioProcedimento.getDescricao());
//			System.out.println("DATA vINCULO: "+usuarioProcedimento.getDataVinculo());
//			System.out.println("PROCEDIMENTO: "+usuarioProcedimento.getProcedimento().getRefpt());
//		}
		
//		Usuario usuario = new Usuario();
//		usuario.setIdUsuario(54L);
//
//		List<UsuarioProcedimento> usuarioProcedimentosPorUsuarios =  usuProcDao.pesquisarPorUsuario(usuario);
//		
//		for (UsuarioProcedimento usuarioProcedimento : usuarioProcedimentosPorUsuarios) {
//			
//			System.out.println("USUARIO: "+usuarioProcedimento.getUsuario().getNome());
//			System.out.println("DESCRICAO: "+usuarioProcedimento.getDescricao());
//			System.out.println("DATA vINCULO: "+usuarioProcedimento.getDataVinculo());
//			System.out.println("PROCEDIMENTO: "+usuarioProcedimento.getProcedimento().getRefpt());
//		}
//		
		
//		List<UsuarioProcedimento> usuarioProcedimentos=  usuProcDao.pesquisarPor("descricao", "Executor");
//		for (UsuarioProcedimento usuarioProcedimento : usuarioProcedimentos) {
//			
//			System.out.println("USUARIO: "+usuarioProcedimento.getUsuario().getNome());
//			System.out.println("DESCRICAO: "+usuarioProcedimento.getDescricao());
//			System.out.println("DATA vINCULO: "+usuarioProcedimento.getDataVinculo());
//			System.out.println("PROCEDIMENTO: "+usuarioProcedimento.getProcedimento().getRefpt());
//		}
//	}

}
