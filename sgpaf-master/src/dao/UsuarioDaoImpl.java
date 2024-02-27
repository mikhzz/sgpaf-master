package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import interfaces.UsuarioDAO;
import model.daoNovo.db.Usuario;
import model.util.GeradoraHash;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDAO, Serializable {

	@Override
	public List<Usuario> pesquisarTodos(int pAtivo, int pHabilitado) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Usuario.class);
		crit.add(Restrictions.eq("ativo", pAtivo));
		crit.add(Restrictions.eq("habilitado", pHabilitado));
		List<Usuario> usuarios = crit.list();
		session.close();
		return usuarios;
	}

	@Override
	public List<Usuario> pesquisarPor(String pColunaJava, String pConsulta, int pAtivo, int pHabilitado)
			throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Usuario.class);
		crit.add(Restrictions.like(pColunaJava, "%" + pConsulta + "%"));
		crit.add(Restrictions.eq("ativo", pAtivo));
		crit.add(Restrictions.eq("habilitado", pHabilitado));
		List<Usuario> usuarios = crit.list();
		session.close();
		return usuarios;
	}

	@Override
	public boolean editarSemSenha(Usuario usr) throws HibernateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario pesquisarPorID(Long pID, int pAtivo, int pHabilitado) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Usuario.class);
		crit.add(Restrictions.eq("idUsuario", pID));
		crit.add(Restrictions.eq("ativo", pAtivo));
		crit.add(Restrictions.eq("ativo", pHabilitado));
		Usuario usr = (Usuario) crit.uniqueResult();
		session.close();
		return usr;
	}

	@Override
	public Usuario logar(String pLogin, String pSenha) throws HibernateException {
		Session session = HibernateUtil.abreConexao();
		Criteria crit = session.createCriteria(Usuario.class);
		crit.add(Restrictions.eq("login", pLogin));
		crit.add(Restrictions.eq("senha", pSenha));
		crit.add(Restrictions.eq("ativo", 1));
		crit.add(Restrictions.eq("habilitado", 1));
		Usuario usr = (Usuario) crit.uniqueResult();
		session.close();
		return usr;
	}
	public static String gerarHashUsr(String pSenha) {

		String password = null;

		password = GeradoraHash.stringHexa(GeradoraHash.gerarHash(pSenha, "SHA-256"));

		return password;
	}

	public static void main(String[] args) {
		UsuarioDaoImpl usudao = new UsuarioDaoImpl();
//		================================================TESTE login.================================================
//		Usuario usr = usudao.logar("PAULO", usudao.gerarHashUsr("123"));
//		System.out.println(usr.getNome());
		// ================================================TESTE PESQUISAR
		// ID.================================================
//			Usuario usr = usudao	.pesquisarPorID(7L, 1,1);
//			System.out.println("NOME: "+ usr.getNome());
//			================================================TESTE SALVAR.================================================
//	Perfil perfil1  = new Perfil();
//	Perfil perfil2  = new Perfil();
//	perfil1.setIdPerfil(1L);
//	perfil2.setIdPerfil(2L);
//	Usuario usr1 = new Usuario();
//	Usuario usr2 = new Usuario();
//			
//	usr1.setCargo("11111");
//	usr1.setCpf("1111");
//	usr1.setLogin("1111");
//	usr1.setNome("111");
//	usr1.setPerfil(perfil1);
//	usr1.setSenha("123");
//	usr1.setAtivo(1);
//	//usr1.setHabilitado(1);
//
//	usr2.setAtivo(1);
//	usr2.setHabilitado(1);
//			usr2.setCargo("2222");
//			usr2.setCpf("2222");
//			usr2.setLogin("2222");
//			usr2.setNome("2222");
//			usr2.setPerfil(perfil2);
//			usr2.setSenha("123");
//			if(usudao.salvarOuAlterar(usr1)) {
//				System.out.println("OK");
//			}else {
//				System.out.println("DEU RUIM");
//			}
//			usudao.salvarOuAlterar(usr2);

//			================================================TESTE alterar.================================================
//		Perfil perfil1 = new Perfil();
//		Perfil perfil2 = new Perfil();
//		perfil1.setIdPerfil(1L);
//		perfil2.setIdPerfil(2L);
//
//		Usuario usr1 = new Usuario();
//		Usuario usr2 = new Usuario();
//		usr1.setIdUsuario(23L);
//		usr1.setCargo("teste");
//		usr1.setCpf("teste");
//		usr1.setLogin("teste");
//		usr1.setNome("teste");
//		usr1.setPerfil(perfil2);
//		usr1.setSenha("teste");
//		
//		usr2.setIdUsuario(24L);
//		usr2.setCargo("teste");
//		usr2.setCpf("teste");
//		usr2.setLogin("teste");
//		usr2.setNome("teste");
//		usr2.setPerfil(perfil1);
//		usr2.setSenha("teste");
//		if (usudao.salvarOuAlterar(usr1)) {
//			System.out.println("OK");
//		} else {
//			System.out.println("DEU RUIM");
//		}
//		usudao.salvarOuAlterar(usr2);

//			================================================ TESTE excluir.================================================
//			Usuario usr1 = new Usuario();
//			usr1.setCargo("aa");
//			usr1.setCpf("sdsds");
//			usr1.setNome("testge");
//			usr1.setSenha("teste");
//			usr1.setLogin("teste");
//			usr1.setIdUsuario(23L);
//			if(usudao.excluir(usr1)) {
//				System.out.println("OK");
//			}else {
//				System.out.println("DEU RUIM");
//			}

//			================================================TESTE PESQUISAR todos.================================================
			List<Usuario> usuarios = usudao.pesquisarTodos(1,1);
			for (Usuario u : usuarios) {
	            System.out.println("login: " + u.getLogin());
	        }

//			================================================TESTE PESQUISAR POR ..args.================================================

//			List<Usuario> usuarios = usudao.pesquisarPor("nome","A",1,1);
//			for (Usuario u : usuarios) {
//	            System.out.println("login: " + u.getLogin());
//	        }
//		================================================TESTE PESQUISAR POR ..consultaComparadorPorColuna.================================================
	}
}
