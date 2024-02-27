package model.daoNovo.base;

import java.util.ArrayList;



public class Table {
	protected String name;
	protected ArrayList<Colum> colums = new ArrayList<Colum>(); 
	public Table() {
		super();
	}
	public ArrayList<String> getNames(){
		ArrayList<String> a =new ArrayList<String>();
		for(int i=0;i<colums.size();i++) {
			a.add(colums.get(i).getName());
		}
		
		return a;
	}
	public ArrayList<String> getTipes(){
		ArrayList<String> a =new ArrayList<String>();
		for(int i=0;i<colums.size();i++) {
			a.add(colums.get(i).getType());
		}
		
		return a;
	}
	public boolean addColumm(Colum colum){
		return colums.add(colum);
	}
	public Colum getColumn(int columIndex){
		return colums.get(columIndex);
	}
	public Colum getColummJava(String columName){
		for(Colum colum :colums) {
			if(colum.getJavaName().equals(columName)){
				return colum;
			}
		}
		return null;
	}
	public Colum getColumm(String columName){
		for(Colum colum :colums) {
			if(colum.getName().equals(columName)){
				return colum;
			}
		}
		return null;
	}
	public Integer getColummIndexJ(String columName){
		for(int i = 0;i<colums.size();i++) {
			if(colums.get(i).getJavaName().equals(columName)){
				return i;
			}
		}
		return null;
	}
	public Integer getColummIndex(String columName){
		for(int i = 0;i<colums.size();i++) {
			if(colums.get(i).getName().equals(columName)){
				return i;
			}
		}
		return null;
	}


	public ArrayList<Colum> getColums(){
		return colums;
	}
	public String getName(){
		return name;
	}
	public Colum getIdCollum(){
		return colums.get(0);
	}

	public String getId(){
		return colums.get(0).getName();
	}
	public String getIdType(){
		return colums.get(0).getType();
	}
}
