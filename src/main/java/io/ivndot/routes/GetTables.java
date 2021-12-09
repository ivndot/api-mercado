package io.ivndot.routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ivndot.beans.ColumnBean;
import io.ivndot.beans.TableBean;
import io.ivndot.beans.TablesResponseBean;
import io.ivndot.dao.ColumnDAO;
import io.ivndot.dao.TableDAO;
import io.ivndot.exception.DAOInitializationException;
import io.ivndot.util.ResponseUtil;

@WebServlet("/tables")
public class GetTables extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// lists
		List<TableBean> tableList = new ArrayList<TableBean>();
		List<ColumnBean> columnList = null;
		List<String> tableNames = null;
		List<String> columnNames = null;
		List<String> columnValues = null;
		// column data type
		String columnDataType = null;

		// beans
		TableBean table = null;
		ColumnBean column = null;

		// daos
		TableDAO tableDAO = null;
		ColumnDAO columnDAO = null;

		try {
			tableDAO = new TableDAO();
			columnDAO = new ColumnDAO();

			// get the names of the tables in the database
			tableNames = tableDAO.getTableNames();

			for (String tableName : tableNames) {
				// for each table name in the array

				// get columns of the given table
				columnNames = columnDAO.getColumnNames(tableName);

				// create column list object
				columnList = new ArrayList<ColumnBean>();

				for (String columnName : columnNames) {
					// for each column name in the array

					// get the values of the given column
					columnValues = columnDAO.getColumnValues(tableName, columnName);

					// get data type of the given table
					columnDataType = columnDAO.getColumnDataType(tableName, columnName);

					// create ColumnBean object
					column = new ColumnBean();

					// set data to the object
					column.setColumnName(columnName);
					column.setDataType(columnDataType);
					column.setValues(columnValues);

					// add to the list of columns
					columnList.add(column);
				}

				// create TableBean object
				table = new TableBean();

				// set data to the object
				table.setTableName(tableName);
				table.setColumns(columnList);

				// add to the list of tables
				tableList.add(table);

			}

			// OK: there was no error
			// send JSON response to the client
			ResponseUtil.sendJSONResponse(resp,
					new TablesResponseBean(100, "ok", "Se obtuvieron correctamente los datos", tableList), "GET");

		} catch (ClassNotFoundException | SQLException | DAOInitializationException e) {
			// ERROR: there was an error in the query
			// send JSON response to the client
			ResponseUtil.sendJSONResponse(resp,
					new TablesResponseBean(200, "error", "Ocurrio un error en la obtencion de los datos", tableList),
					"GET");

			e.printStackTrace();
		} finally {
			// close connection
			columnDAO.closeConnection();
			tableDAO.closeConnection();
		}

	}

}
