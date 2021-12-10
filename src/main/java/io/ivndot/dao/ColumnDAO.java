package io.ivndot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.ivndot.exception.DAOInitializationException;

public class ColumnDAO extends DataAccessObject {

	public ColumnDAO() throws ClassNotFoundException, SQLException {
		// initialize connection
		super();
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to get the names of columns of the specified table
	 * 
	 * @param table Table name
	 * @return List of column names
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public List<String> getColumnNames(String table) throws SQLException, DAOInitializationException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> columnNamesList = new ArrayList<String>();

		String sql = "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE "
				+ "`TABLE_SCHEMA`=? AND `TABLE_NAME`=?";

		try {
			ps = prepareStatement(sql);
			ps.setString(1, getDatabase());
			ps.setString(2, table);

			rs = ps.executeQuery();

			while (rs.next()) {
				// add column names to the list
				columnNamesList.add(rs.getString(1));
			}

			return columnNamesList;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to get the data type of the column
	 * 
	 * @param table  Table name
	 * @param column Colum name
	 * @return The data type of the column
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public String getColumnDataType(String table, String column) throws SQLException, DAOInitializationException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String columnDataType = null;

		String sql = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE table_name = ? AND COLUMN_NAME = ?";

		try {
			ps = prepareStatement(sql);
			ps.setString(1, table);
			ps.setString(2, column);

			rs = ps.executeQuery();

			while (rs.next()) {
				// save data type of the given column
				columnDataType = rs.getString(1);
			}

			return columnDataType;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to get the values of the specified column of a table
	 * 
	 * @param table  Table name
	 * @param column Colum name
	 * @return List of distinct values
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public List<String> getColumnValues(String table, String column) throws SQLException, DAOInitializationException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> columnValuesList = new ArrayList<String>();

		String sql = "SELECT DISTINCT " + column + " FROM " + table;

		try {
			ps = prepareStatement(sql);
			// ps.setString(1, table);
			// ps.setString(2, table);

			rs = ps.executeQuery();

			while (rs.next()) {
				// add values to the list
				columnValuesList.add(rs.getString(1));
			}

			return columnValuesList;

		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	}

}
