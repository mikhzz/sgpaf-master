package model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.daoNovo.db.Empresa;

public class GeradorDePlanilha{
	
	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha ser� salva
	 * @param produtos       a lista de produtos
	 * 
	 * @return uma mensagem informando ao usu�rio o que ocorreu.
	 */
	public String gerarPlanilha(String caminhoArquivo, List<Empresa> empresas) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet("Produtos");

		int linhaAtual = 0;

		// Criar o cabe�alho (header)
		String[] nomesColunas = { "ID", "Nome", "CNPJ", "ENDERE�O", "TELEFONE", "TIPO", "EMAIL", "RESPONSAVEL" };
		criarCabecalho(nomesColunas, aba, linhaAtual);
		linhaAtual++;
		
		// Preencher as linhas com as Empresas
		criarLinhasEmpresas(empresas, aba, linhaAtual);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasEmpresas(List<Empresa> empresas, XSSFSheet aba, int posicaoLinhaAtual) {
		for (Empresa p : empresas) {
			// criar uma nova linha na planilha
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			// Preencher as c�lulas com os atributos da Empresa p
			linhaAtual.createCell(0).setCellValue((((model.daoNovo.db.Empresa) p).getIdEmpresa()));
			linhaAtual.createCell(1).setCellValue(((model.daoNovo.db.Empresa) p).getNome());
			linhaAtual.createCell(2).setCellValue(((model.daoNovo.db.Empresa) p).getCnpj());
			linhaAtual.createCell(3).setCellValue(((model.daoNovo.db.Empresa) p).getEndereco());
			linhaAtual.createCell(4).setCellValue(((model.daoNovo.db.Empresa) p).getTelefone());
			linhaAtual.createCell(5).setCellValue(((model.daoNovo.db.Empresa) p).getTipo());
			linhaAtual.createCell(6).setCellValue(((model.daoNovo.db.Empresa) p).getEmail());
			linhaAtual.createCell(7).setCellValue(((model.daoNovo.db.Empresa) p).getResponsavel());

			
			posicaoLinhaAtual++;
		}

	}

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;
		// Para mudar o estilo:
		// https://stackoverflow.com/questions/43467253/setting-style-in-apache-poi
		for (int i = 0; i < nomesColunas.length; i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas[i]);
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
