package model.daoNovo.base;

import java.util.List;

public class JoinAgregator<T,J,L> {
private T t;
private List<J> joinList;
private List<L> listList;
public T getT() {
	return t;
}
public void setT(T t) {
	this.t = t;
}
public List<J> getJoinList() {
	return joinList;
}
public void setJoinList(List<J> joinList) {
	this.joinList = joinList;
}
public List<L> getListList() {
	return listList;
}
public void setListList(List<L> listList) {
	this.listList = listList;
}


}
