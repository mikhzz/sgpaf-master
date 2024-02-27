package model.boNovo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.daoNovo.PerfilDAO;
import model.daoNovo.db.Perfil;
import model.daoNovo.db.Usuario;

public class PerfilBO {
	private static final PerfilDAO perfilDAO = new PerfilDAO();

	private static Map<Integer, Perfil> perfilCache = null;

	public List<Perfil> getPerfils() {
		perfilCache = new LinkedHashMap<Integer, Perfil>();
		List<Perfil> listaPefil = perfilDAO.consultarTodos();

		for (Perfil perfil : listaPefil) {
//			perfilCache.put(perfil.getIdPerfil(), perfil);
		}
		return listaPefil;
	}

	public Perfil getPerfil(Integer id) {

		return perfilDAO.pesquisarPorId(id);
	}

//	public Perfil getPerfil(Usuario usuario) {
//		return getPerfil(usuario.getPerfil().getIdPerfil());
//	}

}
