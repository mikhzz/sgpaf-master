package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import interfaces.UsuarioEmpresaDAO;
import model.daoNovo.db.UsuarioEmpresa;

public class UsuarioEmpresaDaoImpl extends BaseDaoImpl<UsuarioEmpresa, Long>
		implements UsuarioEmpresaDAO, Serializable {

	@Override
	public List<UsuarioEmpresa> listarTodas(int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from UsuarioEmpresa where ativo = " + pAtivo);
		List<UsuarioEmpresa> usuariosEmpresas = consulta.list();
		session.close();
		return usuariosEmpresas;
	}

	@Override
	public List<UsuarioEmpresa> pesquisarPor(String pColunaBanco, String pConsulta, int pAtivo)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery(
				"from UsuarioEmpresa where " + pColunaBanco + " = " + pConsulta + " and ativo = " + pAtivo);
//        consulta.setMaxResults(10);
		List<UsuarioEmpresa> usuariosEmpresas = consulta.list();
		session.close();
		return usuariosEmpresas;
	}

	@Override
	public List<UsuarioEmpresa> pesquisarPorUsuarioEmpresa(String colunaBancoUsr, String consultaUsr,
			String colunaBancoEmp, String consultaEmp, int pAtivo) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Query consulta = session.createQuery("from UsuarioEmpresa where " + colunaBancoUsr + " = " + consultaUsr
				+ " and " + colunaBancoEmp + " = " + consultaEmp + " and ativo = " + pAtivo);
//        consulta.setMaxResults(10);
		List<UsuarioEmpresa> usuariosEmpresas = consulta.list();
		session.close();
		return usuariosEmpresas;
	}

//	public static void main(String[] args) {
//		UsuarioEmpresaDaoImpl usrEmpDao = new UsuarioEmpresaDaoImpl();
//		================================================TESTE SALVAR.================================================
//		UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
//		Usuario usr = new Usuario();
//		Empresa emp = new Empresa();
//		usr.setIdUsuario(12L);
//		emp.setIdEmpresa(1L);
//		usuarioEmpresa.setEmpresa(emp);
//		usuarioEmpresa.setUsuario(usr);
//		if (usrEmpDao.salvarOuAlterar(usuarioEmpresa)) {
//			System.out.println("SUCESSO");
//		} else {
//			System.out.println("DEU RUIMM");
//		}
//		================================================TESTE ALTERAR.================================================

//		UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
//		Usuario usr = new Usuario();
//		Empresa emp = new Empresa();
//		usr.setIdUsuario(7L);
//		emp.setIdEmpresa(2L);
//		usuarioEmpresa.setIdUsuarioEmpresa(21L);
//		usuarioEmpresa.setEmpresa(emp);
//		usuarioEmpresa.setUsuario(usr);
//		if(usrEmpDao.salvarOuAlterar(usuarioEmpresa)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIMM");
//		}
//		================================================TESTE EXCLUIR.================================================

//		UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
//		Usuario usr = new Usuario();
//		Empresa emp = new Empresa();
//		usr.setIdUsuario(7L);
//		emp.setIdEmpresa(2L);
//		usuarioEmpresa.setIdUsuarioEmpresa(21L);
//		usuarioEmpresa.setEmpresa(emp);
//		usuarioEmpresa.setUsuario(usr);
//		if(usrEmpDao.excluir(usuarioEmpresa)) {
//			System.out.println("SUCESSO");
//		}else {
//			System.out.println("DEU RUIMM");
//		}
//		================================================TESTE PESQUISAR todos.================================================

//		List<UsuarioEmpresa> usrsEmps = usrEmpDao.listarTodas(1);
//		for (UsuarioEmpresa usrsEmp : usrsEmps) {
//			System.out.println(usrsEmp.getUsuario().getNome()+" <=> "+usrsEmp.getEmpresa().getNome()+" VINCULADO");
//		}
//		================================================TESTE PESQUISAR POR EMPRESA.================================================
//		Empresa empresa = new Empresa();
//		empresa.setIdEmpresa(3L);
//		List<UsuarioEmpresa> usrsEmps = usrEmpDao.pesquisarPor("ID_EMPRESA", empresa.getIdEmpresa().toString(), 1);
//		for (UsuarioEmpresa usrsEmp : usrsEmps) {
//			System.out.println(usrsEmp.getUsuario().getNome()+" <=> "+usrsEmp.getEmpresa().getNome()+" VINCULADO");
//		}
//		================================================TESTE PESQUISAR POR USUARIO.================================================
//		Usuario usr = new Usuario();
//		usr.setIdUsuario(6L);
//		List<UsuarioEmpresa> usrsEmps = usrEmpDao.pesquisarPor("ID_USUARIO", usr.getIdUsuario().toString(),1);
//		for (UsuarioEmpresa usrsEmp : usrsEmps) {
//			System.out.println(usrsEmp.getUsuario().getNome()+" <=> "+usrsEmp.getEmpresa().getNome()+" VINCULADO");
//		}
//		================================================TESTE PESQUISAR POR USUARIO/EMPRESA.================================================
//		Usuario usr = new Usuario();
//		usr.setIdUsuario(6L);
//		Empresa emp = new Empresa();
//		emp.setIdEmpresa(3L);
//		
//		List<UsuarioEmpresa> usrsEmps = usrEmpDao.pesquisarPorUsuarioEmpresa("ID_USUARIO", usr.getIdUsuario().toString(),"ID_EMPRESA", emp.getIdEmpresa().toString(),1);
//		for (UsuarioEmpresa usrsEmp : usrsEmps) {
//			System.out.println(usrsEmp.getUsuario().getNome()+" <=> "+usrsEmp.getEmpresa().getNome()+" VINCULADO");
//		}
//	}
}
