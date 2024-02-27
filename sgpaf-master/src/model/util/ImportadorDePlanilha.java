package model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.BalanceteDaoImpl;
import model.boNovo.BalanceteBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;


public class ImportadorDePlanilha {
	private static final BalanceteDaoImpl balanDao = new BalanceteDaoImpl();
	private BalanceteBO balanBO = new BalanceteBO();
	private int ofsetColuna = 0;
	private int ofsetLihna = 1;
	private String regexNumeros= "[^\\d\\-]";


	public String importarLancamentos(Balancete pBalancete, File arquivo) {
		
		String mensagem = "";
		XSSFWorkbook planilha = null;
		try {
			// Create Workbook instance holding reference to .xlsx file
			FileInputStream fis = new FileInputStream(arquivo);
			planilha = new XSSFWorkbook(fis);

			// Pega a primeira aba da planilha
			XSSFSheet abaPlanilha = planilha.getSheetAt(0);

			// Obt�m o iterador de linhas da planilha escolhida
			Iterator<Row> iteradorLinha = abaPlanilha.iterator();

			int reff = 0;
			int sucesso = 0;
			int falha = 0;

			// Pula as primeiras linhas (cabe�alho da planilha)
			while (reff < ofsetLihna && iteradorLinha.hasNext() ) {
				Row linhaAtual = iteradorLinha.next();
				reff++;
			}
			while (iteradorLinha.hasNext()) {
				Row linhaAtual = iteradorLinha.next();
				reff++;

				Lancamento lancamento = criarLancamento(linhaAtual, pBalancete, reff);
				lancamento = CalculoAH.calcularAHLancamento(lancamento);
				
				if (lancamento != null) {
					if(pBalancete.getLancamentos()==null) {
						List<Lancamento> lancamentos = new ArrayList<Lancamento>();
						pBalancete.setLancamentos(lancamentos);
					}
					pBalancete.getLancamentos().add(lancamento);
				}else {
					falha++;
				}
				System.out.println("linha(" + reff + ")");
			}
			if(balanDao.salvarOuAlterar(pBalancete)) {
				System.out.println("LANCAMENTOS SALVO COM SUCESSO TAMANHO LISTA"+pBalancete.getLancamentos().size());
			}
			mensagem = "(" + pBalancete.getLancamentos().size() + ") lancamentos Incluidos com Sucesso";
			if (falha > 0) {
				mensagem = "(" + falha + ") lancamentos N�O incluido";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (planilha != null) {
				try {
					planilha.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return mensagem;
	}
	Integer parseInt(String s){
		try {
		return (new BigDecimal(s).multiply(new BigDecimal(100))).intValue();
		}catch (Exception e) {
			System.out.println(s);
			 e.printStackTrace();
			return null;
		}

	}

	public Lancamento criarLancamento(Row linhaAtual, Balancete pBalancete, int reff) {
		Lancamento p = null;

		if (linhaAtual.getCell(0 + ofsetColuna) != null && linhaAtual.getCell(1 + ofsetColuna) != null
				&& linhaAtual.getCell(2 + ofsetColuna) != null && linhaAtual.getCell(3 + ofsetColuna) != null) {

			Cell celulaRefpt = linhaAtual.getCell(0 + ofsetColuna);
			Cell celulaNaturezaConta = linhaAtual.getCell(1 + ofsetColuna);
			Cell celulaNivel = linhaAtual.getCell(2 + ofsetColuna);
			Cell celulaCodigoEstuturado = linhaAtual.getCell(3 + ofsetColuna);
			Cell celulaCodigoReduzido = linhaAtual.getCell(4 + ofsetColuna);
			Cell celulaDescricao = linhaAtual.getCell(5 + ofsetColuna);
			Cell celulaSaldoInicial = linhaAtual.getCell(6 + ofsetColuna);
			Cell celulaDebitos = linhaAtual.getCell(7 + ofsetColuna);
			Cell celulaCreditos = linhaAtual.getCell(8 + ofsetColuna);
			Cell celulaSaldoFinal = linhaAtual.getCell(9 + ofsetColuna);

			p = new Lancamento();
			p.setBalancete(pBalancete);

			try {
				System.out.println("----------------------------------------------------------------"+celulaRefpt.toString().trim()+"linha(" + reff + ")");
				p.setRefpt(celulaRefpt.toString().trim());
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaNaturezaConta.getStringCellValue().toString().trim()+"linha(" + reff + ")");
				p.setNaturezaConta(celulaNaturezaConta.getStringCellValue().toString().trim());
				System.out.println(p.getNaturezaConta());
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+Math.round(celulaNivel.getNumericCellValue())+"linha(" + reff + ")");
				p.setNivel((int)(Math.round(celulaNivel.getNumericCellValue())));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaCodigoEstuturado.toString().trim()+"linha(" + reff + ")");
				p.setCodigoEstruturado(celulaCodigoEstuturado.toString().trim());
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+parseInt(celulaCodigoReduzido.toString().trim())+"linha(" + reff + ")");
				p.setCodigoReduzido(parseInt(celulaCodigoReduzido.toString().trim()));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaDescricao.toString().trim()+"linha(" + reff + ")");
				p.setDescricao(celulaDescricao.toString().trim());
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaSaldoInicial.toString()+"linha(" + reff + ")");
				p.setSaldoInicial(new BigDecimal(celulaSaldoInicial.toString()));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaDebitos.toString()+"linha(" + reff + ")");
				p.setDebito(new BigDecimal(celulaDebitos.toString()));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaCreditos.toString()+"linha(" + reff + ")");
				p.setCredito(new BigDecimal(celulaCreditos.toString()));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
			try {
				System.out.println("----------------------------------------------------------------"+celulaSaldoFinal.toString()+"linha(" + reff + ")");
				p.setSaldoFinal(new BigDecimal(celulaSaldoFinal.toString()));
			} catch (Exception e) {
				System.out.println("linha(" + reff + ")");
				e.printStackTrace();
			}
		}

		return p;
	}

}
