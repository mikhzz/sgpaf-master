package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.inst2xsd.SalamiSliceStrategy;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import interfaces.LancamentoDAO;
import model.daoNovo.BalanceteDAO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;
import model.util.CalculoAV;
import net.bytebuddy.implementation.bind.annotation.Super;
import sun.launcher.resources.launcher;

public class LancamentoDaoImpl extends BaseDaoImpl<Lancamento, Long> implements LancamentoDAO, Serializable {

	@Override
	public List<Lancamento> consultarTodosLancamentos() throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Lancamento.class);
		List<Lancamento> lancamentos = crit.list();
		session.close();
		return lancamentos;
	}

	@Override
	public List<Lancamento> consultarTodosBalancete(Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Lancamento.class);
		crit.add(Restrictions.eq("balancete", pBalancete));
		List<Lancamento> lancamentos = crit.list();
		session.close();
		return lancamentos;
	}

	@Override
	public List<Lancamento> consultarPorID(Long pIDLancamento) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Lancamento.class);
		crit.add(Restrictions.eq("idLancamento", pIDLancamento));
		List<Lancamento> lancamentos = crit.list();
		session.close();
		return lancamentos;
	}

	@Override
	public List<Lancamento> pesquisarPor(String colunaJava, String consulta, Balancete pBalancete)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Lancamento.class);
		crit.add(Restrictions.like(colunaJava, "%" + consulta + "%"));
		crit.add(Restrictions.eq("balancete", pBalancete));
		List<Lancamento> lancamentos = crit.list();
		session.close();
		return lancamentos;
	}
	
	@Override
	public List<Lancamento> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from Lancamento where " + pColunaBanco + " " + pComparador + " "
				+ pConsulta + " and id_balancete = " + pBalancete.getIdBalancete());
//        consulta.setMaxResults(10);
		List<Lancamento> lancamentos = consulta.list();
		session.close();
		return lancamentos;
	}

	public static List<Lancamento> consultaGroupByRefPT(Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery(
				"from Lancamento where id_balancete = " + pBalancete.getIdBalancete() + " group by refpt ");
//        consulta.setMaxResults(10);
		List<Lancamento> lancamentos = consulta.list();
		session.close();
		return lancamentos;
	}
	public List<Lancamento> pesquisarPorRef(String pRefPT, Balancete pBalancete)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Lancamento.class);
		crit.add(Restrictions.eq("refpt",  pRefPT));
		crit.add(Restrictions.eq("balancete", pBalancete));
		List<Lancamento> lancamentos = crit.list();
		session.close();
		return lancamentos;
	}
//	public static void calcularTodosAv(Balancete pBalancete) {
//		LancamentoDaoImpl lancamentoDao = new LancamentoDaoImpl();
//		BalanceteDaoImpl balanDao = new BalanceteDaoImpl();

//		List<Lancamento> lancamentosRef = consultaGroupByRefPT(pBalancete);
//		List<Lancamento> lancamentosAv = new ArrayList<Lancamento>();
//
//		for (Lancamento lancamento : lancamentosRef) {
//			if (!lancamento.getRefpt().equals("")) {
//				String refpt = lancamento.getRefpt();
//				List<Lancamento> lancamentos = lancamentoDao.pesquisarPorRef(refpt, pBalancete);
//				lancamentos = calcularAV(lancamentos);
//				for (Lancamento lancamentoAV : lancamentos) {
//					lancamentosAv.add(lancamentoAV);
//				}
//			}
//		}
//		pBalancete.setLancamentos(lancamentosAv);
////		balanDao.salvarOuAlterar(pBalancete);
//	}
//
//	public static List<Lancamento> calcularAV(List<Lancamento> lancamentos) {
//		BigDecimal total = new BigDecimal("0.00");
//
//		for (Lancamento lancamento : lancamentos) {
//			System.out.println("etDebito(): " + lancamento.getDebito());
//			total = lancamento.getDebito().add(total);
//		}
//		System.out.println("VALOR TOTAL DE DEBITOS: " + total);
//		for (Lancamento lancamento : lancamentos) {
//			BigDecimal aV = new BigDecimal("0.00");
//			if (!lancamento.getDebito().equals(aV)) {
//				if (!total.equals(aV)) {
//					aV = lancamento.getDebito().divide(total, 1);
//					System.out.println("VALOR DEBITO : ");
//					aV = aV.multiply(new BigDecimal("100")).setScale(0);
//				}
//			}
//			lancamento.setaV(aV);
//
//		}
//		return lancamentos;
//	}

//	public static Lancamento calcularAHLancamento(Lancamento lancamento) {
//
//		BigDecimal aH = new BigDecimal("0.0");
//		if (lancamento.getSaldoFinal() != null && lancamento.getSaldoInicial() != null) {
//			if (!lancamento.getSaldoFinal().equals(aH)) {
//				if (!lancamento.getSaldoInicial().equals(aH)) {
//					System.out.println("===========================ID DO LANCAMENTO: " + lancamento.getIdLancamento());
//					System.out.println("===========================valor do REF: " + lancamento.getRefpt());
//					System.out.println(
//							"===========================valor do dofinal: " + lancamento.getSaldoFinal().toString());
//					System.out.println("===========================valor do doInicial(): "
//							+ lancamento.getSaldoInicial().toString());
//					aH = lancamento.getSaldoFinal().divide(lancamento.getSaldoInicial(), 1);
//					aH = aH.multiply(new BigDecimal("100")).setScale(0);
//
//					System.out.println("===========================valor do ah: " + aH.toString());
//				}
//			}
//		}
//		lancamento.setaH(aH);
//		return lancamento;
//	}
//
//	public static List<Lancamento> calcularAH(List<Lancamento> lancamentos) {
//
//		for (Lancamento lancamento : lancamentos) {
//
//			BigDecimal aH = new BigDecimal("0.00");
//			if (lancamento.getSaldoFinal() != null && lancamento.getSaldoInicial() != null) {
//				if (!lancamento.getSaldoFinal().equals(aH)) {
//					if (!lancamento.getSaldoInicial().equals(aH)) {
//
//						aH = lancamento.getSaldoFinal().divide(lancamento.getSaldoInicial(), 1);
//						aH = aH.multiply(new BigDecimal("100")).setScale(0);
//						System.out.println(
//								"===========================ID DO LANCAMENTO: " + lancamento.getIdLancamento());
//						System.out.println("===========================valor do REF: " + lancamento.getRefpt());
//						System.out.println("===========================valor do dofinal: "
//								+ lancamento.getSaldoFinal().toString());
//						System.out.println("===========================valor do doInicial(): "
//								+ lancamento.getSaldoInicial().toString());
//						System.out.println("===========================valor do ah: " + aH.toString());
//					}
//				}
//			}
//			lancamento.setaH(aH);
//		}
//		return lancamentos;
//	}

	public static void main(String[] args) {
		LancamentoDaoImpl lancamentoDao = new LancamentoDaoImpl();
//		================================================TESTE SALVAR.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(39L);
//		Lancamento lancamento = new Lancamento();
//		lancamento.setBalancete(balancete);
//		lancamento.setCodigoEstruturado("1.1.20.100");
//		lancamento.setCodigoReduzido(1120100);
//		lancamento.setCredito(new BigDecimal(300.000));
//		lancamento.setDebito(new BigDecimal(200.000));
//		lancamento.setDescricao("TESTE");
//		lancamento.setNaturezaConta("Ativo");
//		lancamento.setNivel(3);
//		lancamento.setRefpt("AC");
//		lancamento.setSaldoFinal(new BigDecimal(600.000));
//		lancamento.setSaldoInicial(new BigDecimal(500.000));
//		
//		v
//		================================================TESTE alterar.================================================

//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(39L);
//		Lancamento lancamento = new Lancamento();
//		lancamento.setIdLancamento(6978L);
//		lancamento.setBalancete(balancete);
//		lancamento.setCodigoEstruturado("1.1.20.100");
//		lancamento.setCodigoReduzido(1120100);
//		lancamento.setCredito(new BigDecimal(300.000));
//		lancamento.setDebito(new BigDecimal(200.000));
//		lancamento.setDescricao("TESTEeeeeeee");
//		lancamento.setNaturezaConta("Ativo");
//		lancamento.setNivel(3);
//		lancamento.setRefpt("AC");
//		lancamento.setSaldoFinal(new BigDecimal(600.000));
//		lancamento.setSaldoInicial(new BigDecimal(500.000));
//		
//		if(lancamentoDao.salvarOuAlterar(lancamento)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
		// ================================================TESTE PESQUISAR
		// TODOS================================================
//		List<Lancamento> lancamentos = lancamentoDao.consultarTodosLancamentos();
//		for (Lancamento lancamento : lancamentos) {
//			System.out.println("id lancamento ==> "+lancamento.getIdLancamento());
//			System.out.println("id balancete ==> "+lancamento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+lancamento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
		// ================================================TESTE PESQUISAR POR
		// BALANCETE.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(37L);
//		List<Lancamento> lancamentos = lancamentoDao.consultarTodosBalancete(balancete);
//		for (Lancamento lancamento : lancamentos) {
//			System.out.println("id lancamento ==> "+lancamento.getIdLancamento());
//			System.out.println("id balancete ==> "+lancamento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+lancamento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}

		// ================================================TESTE PESQUISAR
		// POR.================================================
		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(126L);

		BalanceteDaoImpl balanDao = new BalanceteDaoImpl();
		balancete = balanDao.pesquisarPorID(134L, 1);
		CalculoAV.calcularTodosAv(balancete);
		balanDao.salvarOuAlterar(balancete);
		
//		List<Lancamento> lancamentos = lancamentoDao.pesquisarPor("refpt", "AB", balancete);
//		List<Lancamento> lancamentosRef = lancamentoDao.consultaGroupByRefPT(balancete);

//		List<Lancamento> lancamentos = lancamentoDao.consultarTodosBalancete(balancete);
//		for (Lancamento lancamento : lancamentos) {
//			System.out.println("id lancamento ==> " + lancamento.getIdLancamento());
//			System.out.println("REF_PT ==> " + lancamento.getRefpt());
//			System.out.println("id balancete ==> " + lancamento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> " + lancamento.getBalancete().getEmpresa().getNome());
//			System.out.println(
//					"===================================================================================================================");
//		}
//		System.out.println("tamanho ==> " + lancamentos.size());
//		List<Lancamento> lancamentosAv = calcularAV(lancamentos);
//		for (Lancamento lancamento : lancamentosAv) {
//			System.out.println("id lancamento ==> " + lancamento.getIdLancamento());
//			System.out.println("REF_PT ==> " + lancamento.getRefpt());
//			System.out.println("id balancete ==> " + lancamento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> " + lancamento.getBalancete().getEmpresa().getNome());
//			System.out.println("Valor lancamento.getAH ==> " + lancamento.getaH());
//			System.out.println("Valor lancamento.getAv ==> " + lancamento.getaV());
//			System.out.println(
//					"===================================================================================================================");
//		}

		// ================================================TESTE PESQUISAR
		// consultaComparadorPorColuna.================================================
//		Balancete balancete = new Balancete();
//		balancete.setIdBalancete(27L);
//		List<Lancamento> lancamentos = lancamentoDao.consultaComparadorPorColuna("DEBITO", ">", "162588.69", balancete);
//		for (Lancamento lancamento : lancamentos) {
//			System.out.println("id lancamento ==> "+lancamento.getIdLancamento());
//			System.out.println("REF_PT ==> "+lancamento.getRefpt());
//			System.out.println("descricao ==> "+lancamento.getDescricao());
//			System.out.println("debito ==> "+lancamento.getDebito());
//			System.out.println("id balancete ==> "+lancamento.getBalancete().getIdBalancete());
//			System.out.println("empresa ==> "+lancamento.getBalancete().getEmpresa().getNome());
//			System.out.println("===================================================================================================================");
//		}
		// ================================================TESTE PESQUISAR
		// EXCLUIR.================================================
//		Lancamento lancamento = new Lancamento();
//		lancamento.setIdLancamento(6978L);
//		if(lancamentoDao.excluir(lancamento)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIM");
//		}
//		
	}

}
