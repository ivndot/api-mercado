package io.ivndot.beans;

import java.util.List;

public class TableBean {

	private String tableName = null;
	private List<ColumnBean> columns = null;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnBean> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnBean> columns) {
		this.columns = columns;
	}

}
