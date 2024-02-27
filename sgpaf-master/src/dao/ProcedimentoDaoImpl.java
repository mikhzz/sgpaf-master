package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import interfaces.ProcedimentoDAO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;

public class ProcedimentoDaoImpl extends BaseDaoImpl<Procedimento,Long> implements ProcedimentoDAO, Serializable{

	@Override
	public List<Procedimento> listarTodos() throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Procedimento.class);
		List<Procedimento> procedimentos = crit.list();
		session.close();
		return procedimentos;
	}
	@Override
	public List<Procedimento> listarTodosPor(String pColunaJava, String pConsulta, Balancete pBalancete)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Procedimento.class);
		crit.add(Restrictions.eq(pColunaJava,pConsulta));
		crit.add(Restrictions.eq("balancete",pBalancete));
		List<Procedimento> procedimentos = crit.list();
		session.close();
		return procedimentos;
	}
	public Procedimento pesquisaPorRef( String pConsulta, Balancete pBalancete)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Procedimento.class);
		crit.add(Restrictions.eq("refpt",pConsulta));
		crit.add(Restrictions.eq("balancete",pBalancete));
		Procedimento procedimento =(Procedimento) crit.uniqueResult();
		session.close();
		return procedimento;
	}
	
	@Override
	public List<Procedimento> listarTodosPorBalancete(Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Procedimento.class);
		crit.add(Restrictions.eq("balancete",pBalancete));
		List<Procedimento> procedimentos = crit.list();
		session.close();
		return procedimentos;
	}
	@Override
	public List<Procedimento> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from Procedimento where " + pColunaBanco + " " + pComparador + " "
				+ pConsulta + " and id_balancete = " + pBalancete.getIdBalancete());
//        consulta.setMaxResults(10);
		List<Procedimento> procedimentos = consulta.list();
		session.close();
		return procedimentos;
	}
	public static void main(String[] args) {
		ProcedimentoDaoImpl procedimentoDao = new ProcedimentoDaoImpl();
//		================================================TESTE listar todos.================================================
//		List<Procedimento> procedimentos = procedimentoDao.listarTodos();
//		for (Procedimento procedimento : procedimentos) {
//			System.out.println("ID proc ==> "+procedimento.getIdProcedimento());
//			System.out.println("Usuario Executor ==> "+procedimento.getUsuarioExecutor().getNome());
//			System.out.println("ref PT ==> "+procedimento.getRefpt());
//			System.out.println("Conclusão ==> "+procedimento.getConclusoes());
//			System.out.println("Balancete ID ==> "+procedimento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+procedimento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
//		================================================TESTE listarTodosPor.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(25L);
//		String refpt = "AC";
//		List<Procedimento> procedimentos = procedimentoDao.listarTodosPor("refpt", refpt, balancete);
//		for (Procedimento procedimento : procedimentos) {
//			System.out.println("ID proc ==> "+procedimento.getIdProcedimento());
//			System.out.println("Usuario Executor ==> "+procedimento.getUsuarioExecutor().getNome());
//			System.out.println("ref PT ==> "+procedimento.getRefpt());
//			System.out.println("Conclusão ==> "+procedimento.getConclusoes());
//			System.out.println("Balancete ID ==> "+procedimento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+procedimento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
//		================================================TESTE listarTodosPor BALANCETE.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(148L);
//		String refpt = "AA";
//		Procedimento procedimento = procedimentoDao.pesquisaPorRef(refpt, balancete);
//		for (Procedimento procedimento : procedimentos) {
//			System.out.println("ID proc ==> "+procedimento.getIdProcedimento());
//			System.out.println("Usuario Executor ==> "+procedimento.getUsuarioExecutor().getNome());
//			System.out.println("ref PT ==> "+procedimento.getRefpt());
//			System.out.println("Conclusão ==> "+procedimento.getConclusoes());
//			System.out.println("Balancete ID ==> "+procedimento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+procedimento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
//		================================================TESTE consultaComparadorPorColuna.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(31L);
//		List<Procedimento> procedimentos = procedimentoDao.consultaComparadorPorColuna("CONCLUSOES", "LIKE", "'%TES%'", balancete);
//		for (Procedimento procedimento : procedimentos) {
//			System.out.println("ID proc ==> "+procedimento.getIdProcedimento());
//			System.out.println("Usuario Executor ==> "+procedimento.getUsuarioExecutor().getNome());
//			System.out.println("ref PT ==> "+procedimento.getRefpt());
//			System.out.println("Conclusão ==> "+procedimento.getConclusoes());
//			System.out.println("Balancete ID ==> "+procedimento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+procedimento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
//		================================================TESTE SALVAR.================================================
//		Procedimento procedimento = new Procedimento();
//		Balancete balancete = new Balancete();
//		Usuario usr = new Usuario();
//		usr.setIdUsuario(12L);
//		balancete.setIdBalancete(39L);
//		procedimento.setBalancete(balancete);
//		procedimento.setConclusoes("testessstestess");
//		procedimento.setRefpt("AB");
//		procedimento.setUsuarioExecutor(usr);
//		
//		if(procedimentoDao.salvarOuAlterar(procedimento)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
//		================================================TESTE editar.================================================
//		BalanceteDaoImpl balanceteDao = new BalanceteDaoImpl();
//		Balancete balancete = balanceteDao.pesquisarPorID(39L, 1);
//		UsuarioDaoImpl usuDao = new UsuarioDaoImpl();
//		Usuario usr = usuDao.pesquisarPorID(12L, 1, 1);
//		Procedimento procedimento = (Procedimento) procedimentoDao.consultaComparadorPorColuna("idProcedimento", "=", "39", balancete);
//		
//		
//		procedimento.setIdProcedimento(39L);
//		procedimento.setBalancete(balancete);
//		procedimento.setUsuarioExecutor(usr);
//		procedimento.setCheckbox1(true);
//		procedimento.setCheckbox2(true);
//		procedimento.setCheckbox3(true);
//		procedimento.setCheckbox4(true);
//		procedimento.setCheckbox5(true);
//		procedimento.setCheckbox6(true);
//		procedimento.setCheckbox7(true);
//		procedimento.setConclusoes("teste");
//		
//		if(procedimentoDao.salvarOuAlterar(procedimento)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
	}
}
