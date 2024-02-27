package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import interfaces.EmpresaDAO;
import model.daoNovo.db.Empresa;

public class EmpresaDaoImpl extends BaseDaoImpl<Empresa, Long> implements EmpresaDAO, Serializable{

	@Override
	public List<Empresa> pesquisarTodos(int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Empresa.class);
		crit.add(Restrictions.eq("ativo",pAtivo));
        List<Empresa> emrpesas = crit.list();
        session.close();
        
        return emrpesas;
	}

	@Override
	public List<Empresa> pesquisarPor(String pColunaJava, String pConsulta, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Empresa.class);
		crit.add(Restrictions.like(pColunaJava, "%" +pConsulta+"%"));
		crit.add(Restrictions.eq("ativo",pAtivo));
		List<Empresa> empresas = crit.list();
        session.close();
        return empresas;
	}
	
	@Override
	public Empresa pesquisarPorID(Long pID, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Empresa.class);
		crit.add(Restrictions.eq("idEmpresa",pID));
		crit.add(Restrictions.eq("ativo",pAtivo));
		Empresa empresa =(Empresa) crit.uniqueResult();
        session.close();
        return empresa;
	}
	@Override
	public List<Empresa> consultarTipo() throws HibernateException{
		Session session = HibernateUtil.abreConexao();
		Query consulta = session
				.createQuery("DISTINCT(tipo) from Empresa where ativo = 1 ");
//        consulta.setMaxResults(10);
		List<Empresa> empresas = consulta.list();
		session.close();
		return empresas;
	}
//	public static void main(String[] args) {
//		
//		EmpresaDaoImpl empresaDao = new EmpresaDaoImpl();
//		================================================TESTE PESQUISAR POR ID.================================================
//
//		Empresa empresa = empresaDao.pesquisarPorID(1L, 1);
//		System.out.println(empresa.getNome());
//		================================================TESTE LISTAR TODOS.================================================

//		List<Empresa> empresas = empresaDao.pesquisarTodos(1);
//		for (Empresa empresa : empresas) {
//			System.out.println(empresa.getNome());
//		}
//		================================================TESTE LISTAR POR.================================================
//		Long idEmpresa=1L;
//		List<Empresa> empresas = empresaDao.pesquisarPor("tipo", "banco", 1);
//		for (Empresa empresa : empresas) {
//			System.out.println(empresa.getNome());
//		}
//		================================================TESTE SALVAR.================================================
//		Empresa empresa = new Empresa();
//		
//		empresa.setCnpj("55251558556565");
//		empresa.setEmail("admi@teste");
//		empresa.setEndereco("Rua velha");
//		empresa.setNome("TESTE");
//		empresa.setResponsavel("JUCA");
//		empresa.setTelefone("48 995959595");
//		empresa.setTipo("TESTE");
//		
//		if(empresaDao.salvarOuAlterar(empresa)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("dDEU RUIM");
//		}
//		================================================TESTE EDITAR.================================================
		
//		Empresa empresa = new Empresa();
//		empresa.setIdEmpresa(5L);
//		empresa.setAtivo(0);
//		empresa.setCnpj("55251558556565");
//		empresa.setEmail("teste333");
//		empresa.setEndereco("Rua teste333");
//		empresa.setNome("TEST3333E");
//		empresa.setResponsavel("333teste333");
//		empresa.setTelefone("48 3333995959595");
//		empresa.setTipo("testetes3333teteste");
//		
//		if(empresaDao.salvarOuAlterar(empresa)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("dDEU RUIM");
//		}
		
//		================================================TESTE EXCLUIR.================================================
		
//		Empresa empresa = new Empresa();
//		empresa.setIdEmpresa(5L);
//		if(empresaDao.excluir(empresa)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("dDEU RUIM");
//		}
//	}
	
}
