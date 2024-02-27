package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import interfaces.MaterialidadeDAO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Materialidade;

public class MaterialidadeDaoImpl extends BaseDaoImpl<Materialidade, Long> implements MaterialidadeDAO, Serializable {

	@Override
	public Materialidade pesquisarPorBalancete(Balancete pBalancete) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Materialidade.class);
		crit.add(Restrictions.eq("balancete", pBalancete));
		Materialidade materialidade = (Materialidade) crit.uniqueResult();
		session.close();
		return materialidade;
	}

	public static void main(String[] args) {
		Materialidade materialidade = new Materialidade();
		Balancete balancete = new Balancete();
		MaterialidadeDaoImpl mateDao = new MaterialidadeDaoImpl();
		BalanceteDaoImpl balanDao = new BalanceteDaoImpl();

		balancete = balanDao.pesquisarPorID(133L, 1);
		System.out.println("tamanho da lista====>"+balancete.getLancamentos().size());
//		materialidade.setBalancete(balancete);
//		materialidade = mateDao.pesquisarPorBalancete(balancete);
//		System.out.println(materialidade.getIdMaterialidade());

//		List<Lancamento> tempPersons = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("AA")|| p.getRefpt().equals("AB"))
//				.collect(Collectors.toList());
		BigDecimal soma2=  balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("AA")).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
//		Double soma = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("AA")).mapToDouble((Lancamento p) ->p.getSaldoFinal().doubleValue()).sum();
		System.out.println("soma: "+ soma2);
		//		System.out.println("tamanho da lista====>"+tempPersons.size());
//		for (Lancamento lancamento : tempPersons) {
//			System.out.println("ID: "+ lancamento.getIdLancamento()+" refPT: "+lancamento.getRefpt());
//		}
	}
}
