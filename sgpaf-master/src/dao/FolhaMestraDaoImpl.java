package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import interfaces.FolhaMestraDAO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.FolhaMestra;


public class FolhaMestraDaoImpl extends BaseDaoImpl<FolhaMestra, Long> implements FolhaMestraDAO, Serializable {

	@Override
	public List<FolhaMestra> pesquisarTodos(int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(FolhaMestra.class);
		crit.add(Restrictions.eq("ativo", pAtivo));
		List<FolhaMestra> mestras = crit.list();
		session.close();
		return mestras;
	}

	@Override
	public List<FolhaMestra> pesquisarPor(String pColunaBanco, String pConsulta, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session
				.createQuery("from FolhaMestra where " + pColunaBanco + " = " + pConsulta + " and ativo = " + pAtivo);
//        consulta.setMaxResults(10);
		List<FolhaMestra> mestras = consulta.list();
		session.close();
		return mestras;
	}

	@Override
	public FolhaMestra pesquisarPorID(Long pID, int pAtivo) {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(FolhaMestra.class);
		crit.add(Restrictions.eq("idFolhamestra", pID));
		crit.add(Restrictions.eq("ativo", pAtivo));
		FolhaMestra mestra = (FolhaMestra) crit.uniqueResult();
		session.close();
		return mestra;
	}
	
	public FolhaMestra pesquisarPorBalancete(Balancete pBalancete, int pAtivo) {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(FolhaMestra.class);
		crit.add(Restrictions.eq("balancete", pBalancete));
		crit.add(Restrictions.eq("ativo", pAtivo));
		FolhaMestra mestra = (FolhaMestra) crit.uniqueResult();
		session.close();
		return mestra;
	}
	
	@Override
	public List<FolhaMestra> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Balancete pBalancete, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from FolhaMestra where " + pColunaBanco + " " + pComparador + " "
				+ pConsulta + " and ativo = " + pAtivo + " and id_balancete = " + pBalancete.getIdBalancete());
//        consulta.setMaxResults(10);
		List<FolhaMestra> mestras = consulta.list();
		session.close();
		return mestras;
	}
	public static void main(String[] args) {
		FolhaMestraDaoImpl folhaDao = new FolhaMestraDaoImpl();
//		================================================TESTE PESQUISAR consultaComparadorPorColuna.================================================
		BalanceteDaoImpl balanDao = new BalanceteDaoImpl();
		Balancete balancete = balanDao.pesquisarPorID(146L,1);
		FolhaMestra folha  = folhaDao.pesquisarPorBalancete(balancete, 1);
		
//		List<FolhaMestra>mestras = folhaDao.pesquisarPor("ID_USUARIO_SOCIO_GERENTE", "7", 1);
//		for (FolhaMestra mestra : mestras) {
//			System.out.println("Empresa: "+mestra.getBalancete().getEmpresa().getNome());
//			System.out.println("id folha mestra: "+mestra.getIdFolhamestra());
//			System.out.println("id balancete: "+mestra.getBalancete().getIdBalancete());
//			System.out.println("Conlusão folha Mestra : "+mestra.getConclusao());
//			if(mestra.getUsuarioRevisor()!=null) {
//				System.out.println("usuario revisor : "+mestra.getUsuarioRevisor().getNome());
//			}else {
//				System.out.println("usuario revisor : NULL");
//			}
//			if(mestra.getUsuarioSocioDiretor()!=null) {
//				System.out.println("usuario SOCIO DIRETOR : "+mestra.getUsuarioSocioDiretor().getNome());
//			}else {
//				System.out.println("usuario SOCIO DIRETOR : NULL");
//			}
//			if(mestra.getUsuarioSocioGerente()!=null) {
//				System.out.println("usuario SOCIO Gerente : "+mestra.getUsuarioSocioGerente().getNome());
//			}else {
//				System.out.println("usuario SOCIO Gerente : NULL");
//			}
//			System.out.println("======================================================");
//		}
	// ================================================TESTE PESQUISAR POR ID.================================================
//		FolhaMestra mestra = folhaDao.pesquisarPorID(27L, 1);
//		System.out.println("Empresa: "+mestra.getBalancete().getEmpresa().getNome());
//		System.out.println("id folha mestra: "+mestra.getIdFolhamestra());
//		System.out.println("id balancete: "+mestra.getBalancete().getIdBalancete());
//		System.out.println("Conlusão folha Mestra : "+mestra.getConclusao());
//		if(mestra.getUsuarioRevisor()!=null) {
//			System.out.println("usuario revisor : "+mestra.getUsuarioRevisor().getNome());
//			
//		}else {
//			System.out.println("usuario revisor : NULL");
//		}
//		if(mestra.getUsuarioSocioDiretor()!=null) {
//			System.out.println("usuario SOCIO DIRETOR : "+mestra.getUsuarioSocioDiretor().getNome());
//			
//		}else {
//			System.out.println("usuario SOCIO DIRETOR : NULL");
//		}
//		if(mestra.getUsuarioSocioGerente()!=null) {
//			System.out.println("usuario SOCIO Gerente : "+mestra.getUsuarioSocioGerente().getNome());
//			
//		}else {
//			System.out.println("usuario SOCIO Gerente : NULL");
//		}
//		================================================TESTE PESQUISAR POR consultaComparadorPorColuna.================================================
//		BalanceteDaoImpl balanceteDao = new BalanceteDaoImpl();
//		Balancete balancete = balanceteDao.pesquisarPorID(31L, 1);
//		List<FolhaMestra> mestras = folhaDao.consultaComparadorPorColuna("ID_USUARIO_SOCIO_GERENTE","=", "6",balancete, 1);
//		
//		for (FolhaMestra mestra : mestras) {
//			
//			System.out.println("Empresa: "+mestra.getBalancete().getEmpresa().getNome());
//			System.out.println("id folha mestra: "+mestra.getIdFolhamestra());
//			System.out.println("id balancete: "+mestra.getBalancete().getIdBalancete());
//			System.out.println("Conlusão folha Mestra : "+mestra.getConclusao());
//			if(mestra.getUsuarioRevisor()!=null) {
//				System.out.println("usuario revisor : "+mestra.getUsuarioRevisor().getNome());
//				
//			}else {
//				System.out.println("usuario revisor : NULL");
//			}
//			if(mestra.getUsuarioSocioDiretor()!=null) {
//				System.out.println("usuario SOCIO DIRETOR : "+mestra.getUsuarioSocioDiretor().getNome());
//				
//			}else {
//				System.out.println("usuario SOCIO DIRETOR : NULL");
//			}
//			if(mestra.getUsuarioSocioGerente()!=null) {
//				System.out.println("usuario SOCIO Gerente : "+mestra.getUsuarioSocioGerente().getNome());
//				
//			}else {
//				System.out.println("usuario SOCIO Gerente : NULL");
//			}
//			System.out.println("======================================================");
//		}
//		================================================TESTE PESQUISAR todos.================================================
//		List<FolhaMestra> mestras = folhaDao.pesquisarTodos(0);
//		for (FolhaMestra mestra : mestras) {
//			
//			System.out.println("Empresa: "+mestra.getBalancete().getEmpresa().getNome());
//			System.out.println("id folha mestra: "+mestra.getIdFolhamestra());
//			System.out.println("id balancete: "+mestra.getBalancete().getIdBalancete());
//			System.out.println("Conlusão folha Mestra : "+mestra.getBalancete().getIdBalancete());
//			if(mestra.getUsuarioRevisor()!=null) {
//				System.out.println("usuario revisor : "+mestra.getUsuarioRevisor().getNome());
//				
//			}else {
//				System.out.println("usuario revisor : NULL");
//			}
//			if(mestra.getUsuarioSocioDiretor()!=null) {
//				System.out.println("usuario SOCIO DIRETOR : "+mestra.getUsuarioSocioDiretor().getNome());
//				
//			}else {
//				System.out.println("usuario SOCIO DIRETOR : NULL");
//			}
//			if(mestra.getUsuarioSocioGerente()!=null) {
//				System.out.println("usuario SOCIO Gerente : "+mestra.getUsuarioSocioGerente().getNome());
//				
//			}else {
//				System.out.println("usuario SOCIO Gerente : NULL");
//			}
//			System.out.println("======================================================");
//		}
	}

}
