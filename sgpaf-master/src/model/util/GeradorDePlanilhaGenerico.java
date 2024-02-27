package model.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.daoNovo.db.Empresa;
import model.daoNovo.util.PropertyDescriptorSorter;

public class GeradorDePlanilhaGenerico<entidade> {

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de empresas
	 * 
	 * @param caminhoArquivo onde a planilha ser� salva
	 * @param produtos       a lista de produtos
	 * 
	 * @return uma mensagem informando ao usu�rio o que ocorreu.
	 */
	public String gerarPlanilha(String caminhoArquivo, List<entidade> entidades) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet(entidades.get(0).getClass().getSimpleName());

		int linhaAtual = 0;

		List<PropertyDescriptor> sortedPdList = PropertyDescriptorSorter.getSortedPDList(entidades.get(0).getClass());
		// Criar o cabe�alho (header)
		List<String> nomesColunas = new ArrayList<String>();
		for (PropertyDescriptor propertyDescriptor : sortedPdList) {
			nomesColunas.add(propertyDescriptor.getName());
		}
		criarCabecalho(nomesColunas, aba, linhaAtual);
		linhaAtual++;

		// Preencher as linhas com as entidades
		criarLinhasEmpresas(entidades, aba, linhaAtual, sortedPdList);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasEmpresas(List<entidade> entidades, XSSFSheet aba, int posicaoLinhaAtual,
			List<PropertyDescriptor> sortedPdList) {
		for (entidade ent : entidades) {
			// criar uma nova linha na planilha
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			// Preencher as c�lulas com os atributos da entidades ent
			int i = 0;
			for (PropertyDescriptor propertyDescriptor : sortedPdList) {
				try {
					String value = propertyDescriptor.getReadMethod().invoke(ent).toString();
					linhaAtual.createCell(i).setCellValue(value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				i++;
			}

			posicaoLinhaAtual++;
		}

	}

	private void criarCabecalho(List<String> nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;
		// Para mudar o estilo:
		// https://stackoverflow.com/questions/43467253/setting-style-in-apache-poi
		for (int i = 0; i < nomesColunas.size(); i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas.get(i));
		}
	}

	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}

}
