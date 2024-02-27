package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import interfaces.BalanceteDAO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;

public class BalanceteDaoImpl extends BaseDaoImpl<Balancete, Long> implements BalanceteDAO, Serializable {

	@Override
	public List<Balancete> pesquisarTodos(int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Balancete.class);
		crit.add(Restrictions.eq("ativo", pAtivo));
		List<Balancete> balancetes = crit.list();
		session.close();
		return balancetes;
	}

	@Override
	public List<Balancete> pesquisarPor(String pColunaBanco, String pConsulta, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session
				.createQuery("from Balancete where " + pColunaBanco + " = " + pConsulta + " and ativo = " + pAtivo);
//        consulta.setMaxResults(10);
		List<Balancete> balancetes = consulta.list();
		session.close();
		return balancetes;
	}

	@Override
	public List<Balancete> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Empresa pEmpresa, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from Balancete where " + pColunaBanco + " " + pComparador + " "
				+ pConsulta + " and ativo = " + pAtivo + " and id_empresa = " + pEmpresa.getIdEmpresa());
//        consulta.setMaxResults(10);
		List<Balancete> balancetes = consulta.list();
		session.close();
		return balancetes;
	}
	
	public List<Balancete> pesquisarPorData(Date dataConsulta, Empresa pEmpresa, int inicioFim) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Balancete.class);
		crit.add(Restrictions.eq("empresa", pEmpresa));
		switch (inicioFim) {
		case 0:
			crit.add(Restrictions.eq("dataBalancoInicio", dataConsulta));
			break;
		case 1:
			crit.add(Restrictions.eq("dataBalancoFim", dataConsulta));
			break;
		}
		crit.add(Restrictions.eq("ativo", 1));
		List<Balancete> balancete = crit.list();
		session.close();
		return balancete;
	}
	
	@Override
	public Balancete pesquisarPorID(Long pID, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Balancete.class);
		crit.add(Restrictions.eq("idBalancete", pID));
		crit.add(Restrictions.eq("ativo", pAtivo));
		Balancete balancete = (Balancete) crit.uniqueResult();
		session.close();
		return balancete;
	}

	public static void main(String[] args) {
		BalanceteDaoImpl balanDao = new BalanceteDaoImpl();
//		================================================TESTE SALVAR.================================================
//		Balancete balancete = new Balancete();
//		EmpresaDaoImpl empDao = new EmpresaDaoImpl();
//		Empresa empresa =empDao.pesquisarPorID(2L, 1);
//		System.out.println("EMPRESA: "+empresa.getNome());
//		balancete.setDatabase(2020);
//		balancete.setEmpresa(empresa);
//		balancete.setSaldototal(199999.0);
//		if(balanDao.salvarOuAlterar(balancete)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
//		================================================TESTE EDITAR.================================================
//		Balancete balancete = balanDao.pesquisarPorID(38L, 1);
//		balancete.setDatabase(2019);
//		if(balanDao.salvarOuAlterar(balancete)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
		//		Balancete balancete = balanDao.pesquisarPorID(31L, 1);
//		System.out.println("BALANCETE PERTENCE A EMPRESA ==> "+balancete.getEmpresa().getNome());
//		System.out.println("BALANCETE DATA INICIO ==> "+balancete.getDataBalancoInicio());
//		================================================TESTE PESQUISAR TODOS.================================================
//	List<Balancete> balancetes = balanDao.pesquisarTodos(1);
//	for (Balancete balanceteBEAN : balancetes) {
//		System.out.println("BALANCETE PERTENCE A EMPRESA ==> "+balanceteBEAN.getEmpresa().getNome());
//		System.out.println("BALANCETE DATA INICIO ==> "+balanceteBEAN.getDataBalancoInicio());
//		System.out.println("==================================================================================");
//	}
//		================================================TESTE PESQUISAR todos.================================================
//		List<Balancete> balancetes = balanDao.pesquisarTodos(1);
//		for (Balancete balanceteBEAN : balancetes) {
//			System.out.println("BALANCETE PERTENCE A EMPRESA ==> "+balanceteBEAN.getEmpresa().getNome());
//			System.out.println("BALANCETE DATA INICIO ==> "+balanceteBEAN.getDataBalancoInicio());
//			System.out.println("==================================================================================");
//			if(balanceteBEAN.getLancamentos()!=null) {
//				int count =0;
//				for (Lancamento lancamento : balanceteBEAN.getLancamentos()) {
//					count++;
//					System.out.println("==========================LANCAMENTO=============================NUMERO: "+lancamento.getDescricao());
//				}
//			}
//		}
//		================================================TESTE PESQUISAR POR.================================================
		List<Balancete> balancetes = balanDao.pesquisarPor("id_balancete", "33", 1);
		for (Balancete balanceteBEAN : balancetes) {
			System.out.println(balanceteBEAN.getLancamentos().size());
			System.out.println("BALANCETE PERTENCE A EMPRESA ==> "+balanceteBEAN.getEmpresa().getNome());
			System.out.println("BALANCETE DATA INICIO ==> "+balanceteBEAN.getDataBalancoInicio());
			System.out.println("==================================================================================");
		}
//		================================================TESTE PESQUISAR consultaComparadorPorColuna.================================================
//		EmpresaDaoImpl empDao = new EmpresaDaoImpl();
//		Empresa empresa = empDao.pesquisarPorID(2L, 1);
//		
//		List<Balancete> balancetes = balanDao.consultaComparadorPorColuna("id_balancete", "=", "28", empresa, 1);
//		for (Balancete balanceteBEAN : balancetes) {
//			System.out.println("BALANCETE PERTENCE A EMPRESA ==> "+balanceteBEAN.getEmpresa().getNome());
//			System.out.println("BALANCETE DATA INICIO ==> "+balanceteBEAN.getDataBalancoInicio());
//			System.out.println("==================================================================================");
//		}
	}

}
