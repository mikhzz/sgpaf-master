package controller;


import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.List;

import model.boNovo.UsuarioBO;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.base.Table;
import model.daoNovo.db.Usuario;

public class UsuarioComboBox extends AbstractListModel implements ComboBoxModel {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private static final UsuarioBO usuarioBO = new UsuarioBO();
//	private static final Table tableUsuario = usuarioBO.getTableUsuario();
	
	
	public UsuarioComboBox() {
		super();
		String StringCargo = "Diretor";
		usuarios= usuarioBO
				.consultarWhereFiltros("cargo",  StringCargo,1,1);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.usuarios.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return this.usuarios.get(index);
	}

	@Override
	public void setSelectedItem(Object pUsuario) {
		if(pUsuario instanceof Usuario) {
			this.usuario = (Usuario)pUsuario;
			fireContentsChanged(this.usuarios,0,this.usuarios.size());
		}
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	public void addUsuario(Usuario pUsuario ) {
		this.usuarios.add(pUsuario);
		}
}
