package io.ivndot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ivndot.exception.DAOInitializationException;

public class ProbaDAO extends DataAccessObject {

	public ProbaDAO() throws ClassNotFoundException, SQLException {
		// initialize connection
		super();
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to create a sql view
	 * 
	 * @param table  Name of the table
	 * @param field1 Name of the field one
	 * @param field2 Name of the field two
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public void createView(String table, String field1, String field2) throws SQLException, DAOInitializationException {
		PreparedStatement ps = null;

		// query
		String sql = "create or replace view conjuntas as (select " + field1 + ", " + field2
				+ ", count(*)/(select count(*) from " + table + ") as conjunta from " + table + " where " + field1
				+ " in(select distinct " + field1 + " from " + table + ") and " + field2 + " in(select distinct "
				+ field2 + " from " + table + ") group by " + field2 + ", " + field1 + " order by " + field1 + ", "
				+ field2 + ")";
		try {

			ps = prepareStatement(sql);
			ps.execute();
			System.out.println("================VISTA=============");
			System.out.println(sql);
			System.out.println("================VISTA=============");
		} finally {
			closePreparedStatement(ps);
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to compute the `conjunta` probability
	 * 
	 * @param field1      Field one
	 * @param valueField1 Value of the field one
	 * @param field2      Field two
	 * @param valueField2 Value of the field two
	 * @return `conjunta` probability
	 * @throws SQLException
	 * @throws DAOInitializationException
	 * @throws ClassNotFoundException
	 */
	public String probaConjunta(String field1, String valueField1, String field2, String valueField2)
			throws SQLException, DAOInitializationException, ClassNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String conjunta = null;

		// convert the value of the field if necessary
		valueField1 = convertField(field1, valueField1);
		valueField2 = convertField(field2, valueField2);

		// query
		String sql = "select conjunta from conjuntas where " + field1 + "=" + valueField1 + " and " + field2 + "="
				+ valueField2;

		System.out.println("================CONJUNTA=============");
		System.out.println(sql);
		System.out.println("================CONJUNTA=============");

		try {

			ps = prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				conjunta = rs.getString(1);
			}

			return conjunta;
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
	 * Function to compute the `bayesiana` probability
	 * 
	 * @param field1      Field one
	 * @param valueField1 Value of the field one
	 * @param field2      Field two
	 * @param valueField2 Value of the field two
	 * @return `bayesiana` probability
	 * @throws SQLException
	 * @throws DAOInitializationException
	 * @throws ClassNotFoundException
	 */
	public String probaBayesiana(String field1, String valueField1, String field2, String valueField2)
			throws SQLException, DAOInitializationException, ClassNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bayesiana = null;

		// convert the value of the field if necessary
		valueField1 = convertField(field1, valueField1);
		valueField2 = convertField(field2, valueField2);

		// query
		String sql = "select conjunta/(select sum(conjunta) from conjuntas where " + field1 + "=" + valueField1
				+ ")*(select sum(conjunta) from conjuntas where " + field1 + "=" + valueField1
				+ ")/(select sum(conjunta) from conjuntas where " + field2 + "=" + valueField2
				+ ") as bayesiana from conjuntas where " + field1 + "=" + valueField1 + " and " + field2 + "="
				+ valueField2;

		System.out.println("================BAYESIANA=============");
		System.out.println(sql);
		System.out.println("================BAYESIANA=============");

		try {

			ps = prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				bayesiana = rs.getString(1);
			}

			return bayesiana;
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
	 * Function to compute the `condicional` probability
	 * 
	 * @param field1      Field one
	 * @param valueField1 Value of the field one
	 * @param field2      Field two
	 * @param valueField2 Value of the field two
	 * @return `condicional` probability
	 * @throws SQLException
	 * @throws DAOInitializationException
	 * @throws ClassNotFoundException
	 */
	public String probaCondicional(String field1, String valueField1, String field2, String valueField2)
			throws SQLException, DAOInitializationException, ClassNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String condicional = null;

		// convert the value of the field if necessary
		valueField1 = convertField(field1, valueField1);
		valueField2 = convertField(field2, valueField2);

		// query
		String sql = "select conjunta/(select sum(conjunta) from conjuntas where " + field1 + "=" + valueField1
				+ ") from conjuntas where " + field1 + "=" + valueField1 + " and " + field2 + "=" + valueField2;

		System.out.println("================CONDICIONAL=============");
		System.out.println(sql);
		System.out.println("================CONDICIONAL=============");
		try {

			ps = prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				condicional = rs.getString(1);
			}

			return condicional;
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
	 * Function to convert the value of the field depending on the data type of the
	 * column
	 * 
	 * @param field      The field
	 * @param valueField The value of the field
	 * @return A string with the value of the field enclosed in '' if the data type
	 *         is a string or date, if not only return the same value of the field
	 * @throws SQLException
	 * @throws DAOInitializationException
	 * @throws ClassNotFoundException
	 */
	private String convertField(String field, String valueField)
			throws SQLException, DAOInitializationException, ClassNotFoundException {

		String newValueField = valueField;
		// get the data type of the column
		ColumnDAO columnDAO = new ColumnDAO();
		String dataTypeField = columnDAO.getColumnDataType("conjuntas", field);

		if (dataTypeField.equals("char") || dataTypeField.equals("varchar") || dataTypeField.equals("binary")
				|| dataTypeField.equals("varbinary") || dataTypeField.equals("tinyblob")
				|| dataTypeField.equals("tinytext") || dataTypeField.equals("text") || dataTypeField.equals("blob")
				|| dataTypeField.equals("mediumtext") || dataTypeField.equals("mediumblob")
				|| dataTypeField.equals("longtext") || dataTypeField.equals("longblob") || dataTypeField.equals("date")
				|| dataTypeField.equals("datetime") || dataTypeField.equals("timestamp") || dataTypeField.equals("time")
				|| dataTypeField.equals("year")) {
			// string or date data type
			// append simple ticks to the field
			newValueField = "'" + valueField + "'";
		}

		// close connection
		columnDAO.closeConnection();

		return newValueField;

	}

}
