package interfaces;

import java.util.List;

import org.hibernate.HibernateException;

import model.daoNovo.db.UsuarioEmpresa;

public interface UsuarioEmpresaDAO extends BaseDAO<UsuarioEmpresa, Long>{
	
	List<UsuarioEmpresa> listarTodas(int ativo) throws HibernateException;
	
	List<UsuarioEmpresa> pesquisarPor(String colunaBanco, String consulta,int ativo) throws HibernateException;
	List<UsuarioEmpresa> pesquisarPorUsuarioEmpresa(String colunaBancoUsr, String consultaUsr, String colunaBancoEmp, String consultaEmp,int ativo) throws HibernateException;

}
