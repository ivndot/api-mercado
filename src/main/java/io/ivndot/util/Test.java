package io.ivndot.util;

import java.sql.SQLException;
import java.util.List;

import io.ivndot.beans.TableBean;
import io.ivndot.dao.ColumnDAO;
import io.ivndot.dao.TableDAO;
import io.ivndot.exception.DAOInitializationException;

public class Test {
	public static void main(String[] args) {

		TableDAO tabledao = null;
		ColumnDAO columndao = null;
		TableBean tableBean = null;

		try {
			tabledao = new TableDAO();
			columndao = new ColumnDAO();

			List<String> tables = tabledao.getTableNames();
			List<String> columns = columndao.getColumnNames("abono");
			List<String> columnValues = columndao.getColumnValues("abono", "idempresa");
			
			System.out.println(columndao.getColumnDataType("abono", "idempresa"));

			for (String val : columnValues) {
				System.out.println(val);
			}

		} catch (SQLException | DAOInitializationException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}
