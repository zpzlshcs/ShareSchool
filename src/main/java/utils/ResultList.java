package utils;

import java.util.List;

public class ResultList<T>{
	List<T> dataList;
	int listSize;
	int pageSize;
	int pageNum;
	int allListSize;

	public ResultList(List<T> list,int pageSize,int pageNum,int size){
		dataList = list;
		listSize = list.size();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		allListSize = size;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
}
