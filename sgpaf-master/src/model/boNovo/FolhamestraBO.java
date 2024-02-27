package model.boNovo;

import java.util.List;

import dao.FolhaMestraDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.FolhaMestra;

public class FolhamestraBO {

	private static final FolhaMestraDaoImpl folhamestraDAO = new FolhaMestraDaoImpl();
//	private static final Table tableFolhamestra = folhamestraDAO.getTable();

//	public static Table getTablefolhamestra() {
//		return tableFolhamestra;
//	}

	public List<FolhaMestra> consultarTodosFolhamestras() {
		return folhamestraDAO.pesquisarTodos(1);
	}

//	public List<FolhaMestra> consultarFolhamestras(Balancete balancete) {
//		return consultarFolhamestras(balancete.getIdBalancete());
//	}

	public List<FolhaMestra> consultarFolhamestras(Balancete pBalancete) {
		return folhamestraDAO.pesquisarPor("id_balancete", pBalancete.getIdBalancete().toString(), 1);
	}

//	public Boolean excluirFolhamestra(FolhaMestra folhamestra) {
//		return excluirFolhamestra(folhamestra.getIdFolhamestra());
//	}

	public boolean excluirFolhamestra(FolhaMestra pFolhamestra) {
		return folhamestraDAO.excluir(pFolhamestra);
	}

	public boolean salvarOuAlterarFolhamestra(FolhaMestra pFolhamestra) {
		return folhamestraDAO.salvarOuAlterar(pFolhamestra);
	}

//	public Boolean alterarFolhamestra(FolhaMestra folhamestra) {
//		return folhamestraDAO.alterar(folhamestra, folhamestra.getIdFolhamestra());
//	}

}
