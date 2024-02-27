package model.daoNovo;

import java.beans.PropertyDescriptor;
import java.util.List;

import model.daoNovo.base.AutoDao;
import model.daoNovo.base.AutoTable;
import model.daoNovo.base.Table;
import model.daoNovo.db.Usuario;
import model.daoNovo.util.PropertyDescriptorSorter;

public class UsuarioUpdateSemSenhaDAO extends AutoDao<Usuario,Integer>{
	private static Integer index;
	
	public UsuarioUpdateSemSenhaDAO() {
		super(getCustomTable(),Usuario.class,getCustomPDArray());
	}
	@SuppressWarnings("unlikely-arg-type")
	private static PropertyDescriptor[] getCustomPDArray() {
		List<PropertyDescriptor> sortedPDList = PropertyDescriptorSorter.getSortedPDList(Usuario.class);
		if(index==null) {
			System.err.println("UsuarioUpdateDAO Index error");
		}else {
			sortedPDList.remove(index);
		}
		return sortedPDList.toArray(new PropertyDescriptor[sortedPDList.size()]);
	}
	@SuppressWarnings("unlikely-arg-type")
	private static Table getCustomTable() {
		AutoTable autoTable = new AutoTable(Usuario.class);
		index = autoTable.getColummIndexJ("senha");
		autoTable.getColums().remove(index);
		System.out.println("getCustomTable"+index);

		return autoTable;
	}
}
