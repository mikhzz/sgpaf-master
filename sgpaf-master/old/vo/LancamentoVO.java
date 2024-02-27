package model.vo;

public class LancamentoVO {
	
	
		
		private int idLancamento;
		private int idBalancete; 
		private int tipo; 
		private double debito;
		private double credito;
		
		
		public LancamentoVO() {
			super();
			
		}


		public LancamentoVO(int idLancamento, int idBalancete, int tipo, double debito, double credito) {
			super();
			this.idLancamento = idLancamento;
			this.idBalancete = idBalancete;
			this.tipo = tipo;
			this.debito = debito;
			this.credito = credito;
		}


		public int getIdLancamento() {
			return idLancamento;
		}


		public void setIdLancamento(int idLancamento) {
			this.idLancamento = idLancamento;
		}


		public int getIdBalancete() {
			return idBalancete;
		}


		public void setIdBalancete(int idBalancete) {
			this.idBalancete = idBalancete;
		}


		public int getTipo() {
			return tipo;
		}


		public void setTipo(int tipo) {
			this.tipo = tipo;
		}


		public double getDebito() {
			return debito;
		}


		public void setDebito(double debito) {
			this.debito = debito;
		}


		public double getCredito() {
			return credito;
		}


		public void setCredito(double credito) {
			this.credito = credito;
		}
		
		
		
		
		
		
		

}
