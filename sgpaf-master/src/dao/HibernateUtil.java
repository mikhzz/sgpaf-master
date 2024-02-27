package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Checagem;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Materialidade;
import model.daoNovo.db.Perfil;
import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import model.daoNovo.db.UsuarioFolhaMestra;
import model.daoNovo.db.UsuarioProcedimento;
import model.daoNovo.db.FolhaMestra;

public class HibernateUtil {
	
	
	private static final SessionFactory sessionFactory;
	 
	 static {
	        try {
	            Configuration cfg = new Configuration();
	            cfg.addAnnotatedClass(Usuario.class);
	            cfg.addAnnotatedClass(UsuarioEmpresa.class);
	            cfg.addAnnotatedClass(Balancete.class);
	            cfg.addAnnotatedClass(Perfil.class);
	            cfg.addAnnotatedClass(Empresa.class);
	            cfg.addAnnotatedClass(FolhaMestra.class);
	            cfg.addAnnotatedClass(Lancamento.class);
	            cfg.addAnnotatedClass(Procedimento.class);
	            cfg.addAnnotatedClass(Materialidade.class);
	            cfg.addAnnotatedClass(Checagem.class);
	            cfg.addAnnotatedClass(UsuarioProcedimento.class);
	            cfg.addAnnotatedClass(UsuarioFolhaMestra.class);
	            cfg.configure("/dao/hibernate.cfg.xml");
	            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
	            sessionFactory = cfg.buildSessionFactory(build.build());
	        } catch (Throwable ex) {
	            System.err.println("Erro ao criar Hibernate util." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static Session abreConexao() {
	        return sessionFactory.openSession();
	    }

}
