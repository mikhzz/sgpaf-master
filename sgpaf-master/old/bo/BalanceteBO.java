package model.bo;

import java.util.ArrayList;

import model.dao.BalanceteDAO;
import model.vo.Balancete;

public class BalanceteBO {

	public ArrayList<Balancete> consultarTodosBalancetes() {
		BalanceteDAO balancete = new BalanceteDAO();
		return balancete.consultarTodosBalancetes();
	}

}
