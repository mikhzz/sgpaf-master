package model.daoNovo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.CaseFormat;

public class ConversorModel {
	public static  String nameclass="";
	public static void main(String[] args) {
		File folderTables = new File(ConversorModel.class.getResource("/tables").getFile());
		File folderModels = new File(ConversorModel.class.getResource("/models").getFile());

		File[] fileList = folderTables.listFiles();

		for (File file : fileList) {
			if (file.isFile()) {
				try {
					String txt = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())),
							StandardCharsets.UTF_8);
					txt = converterModel(txt);
					File modelFile = new File(
							folderModels.getAbsolutePath() + "/" +nameclass+".java");
					if (modelFile.exists()) {
						modelFile.delete();
					}
					if (!modelFile.exists()) {
						FileWriter fileWriter = new FileWriter(modelFile);
						fileWriter.write(txt);
						fileWriter.close();
					} else {
						System.out.println("existe");
						System.out.println(modelFile.getAbsolutePath());
					}
					System.out.println(modelFile.getAbsolutePath());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static String converterModel(String modelTxt) {
		
		modelTxt = modelTxt.replaceAll("public", "private");
		modelTxt = modelTxt.replaceFirst("private", "public");

		modelTxt = modelTxt.replaceAll("boolean", "Boolean");
		modelTxt = modelTxt.replaceAll("byte", "Byte");
		modelTxt = modelTxt.replaceAll("char", "Character");
		modelTxt = modelTxt.replaceAll("float", "Float");
		modelTxt = modelTxt.replaceAll("int", "Integer");
		modelTxt = modelTxt.replaceAll("long", "Long");
		modelTxt = modelTxt.replaceAll("short", "Short");
		modelTxt = modelTxt.replaceAll("double", "Double");
		modelTxt = modelTxt.replaceAll("java.sql.Date", "Date");
		

		List<String> linhas = Arrays.asList(modelTxt.split("\n"));
		linhas = new ArrayList<String>(linhas);

		if(modelTxt.contains("Date")) {
			linhas.add(0,"import java.util.Date;");
		}
		linhas.add(0,"package model.daoNovo.db;") ;

		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).contains("public")) {
				String[] split = linhas.get(i).replaceAll("\\{", "").trim().split("\\s");
				String varTableOld = split[split.length - 1];
				String varTableNew = (CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, varTableOld));
				nameclass=varTableNew;
				String linha = linhas.get(i).replaceFirst(varTableOld, varTableNew);
				linhas.set(i, linha);
				continue;
			}
			if (linhas.get(i).contains("private")) {
				String[] split = linhas.get(i).replaceAll(";", "").trim().split("\\s");
				String varNameOld = split[split.length - 1];
				String varNameNew = (CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, varNameOld));
				String linha = linhas.get(i).replaceFirst(varNameOld, varNameNew);
				linhas.set(i, linha);

			}

		}
		modelTxt = "";
		for (int i = 0; i < linhas.size(); i++) {
			modelTxt += (linhas.get(i) + "\n");
		}

		System.out.println(modelTxt);
		return modelTxt;
	}

}
