package io.ivndot.beans;

import java.util.List;

public class TablesResponseBean {

	private int code = 0;
	private String status = null;
	private String description = null;
	private List<TableBean> tables = null;

	/**
	 * Constructor of `tables` end point
	 * 
	 * @param code        Code number [100 = All good; 200 = Error, there was an
	 *                    error consulting the database]
	 * @param status      "ok" or "error"
	 * @param description A little description about the response
	 * @param tables      List of tables fetched from the database
	 */
	public TablesResponseBean(int code, String status, String description, List<TableBean> tables) {
		this.code = code;
		this.status = status;
		this.description = description;
		this.tables = tables;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TableBean> getTables() {
		return tables;
	}

	public void setTables(List<TableBean> tables) {
		this.tables = tables;
	}

}
