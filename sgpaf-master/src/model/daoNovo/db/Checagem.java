package model.daoNovo.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkbox")
public class Checagem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CHECKBOX")
	private Long idChecagem;
	@Column(name = "DESCRICAO")
	private String descricao;
	@Column(name = "SELECIONADO")
	private boolean selecionado;
	@Column(name = "DATA_CHECK")
	private Date dataCheck;
	@ManyToOne
    @JoinColumn(name = "ID_PROCEDIMENTO")
	private Procedimento procedimento;
	
	public Checagem(Long idChecagem, String descricao, boolean selecionado, Procedimento procedimento, Date dataCheck) {
		super();
		this.idChecagem = idChecagem;
		this.descricao = descricao;
		this.selecionado = selecionado;
		this.procedimento = procedimento;
		this.dataCheck = dataCheck;
	}
	public Checagem(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	public Checagem() {
		super();
	}

	public Date getDataCheck() {
		return dataCheck;
	}

	public void setDataCheck(Date dataCheck) {
		this.dataCheck = dataCheck;
	}

	public Long getIdChecagem() {
		return idChecagem;
	}
	public void setIdChecagem(Long idChecagem) {
		this.idChecagem = idChecagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isSelecionado() {
		return selecionado;
	}
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCheck == null) ? 0 : dataCheck.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idChecagem == null) ? 0 : idChecagem.hashCode());
		result = prime * result + ((procedimento == null) ? 0 : procedimento.hashCode());
		result = prime * result + (selecionado ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checagem other = (Checagem) obj;
		if (dataCheck == null) {
			if (other.dataCheck != null)
				return false;
		} else if (!dataCheck.equals(other.dataCheck))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idChecagem == null) {
			if (other.idChecagem != null)
				return false;
		} else if (!idChecagem.equals(other.idChecagem))
			return false;
		if (procedimento == null) {
			if (other.procedimento != null)
				return false;
		} else if (!procedimento.equals(other.procedimento))
			return false;
		if (selecionado != other.selecionado)
			return false;
		return true;
	}
	
	
	
	

}
