package io.ivndot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ivndot.exception.DAOInitializationException;

public class DataAccessObject {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String HOST = "db4free.net";
	private static final String DATABASE = "polizona_53";
	private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "";
	private static final String USER = "polizona_53";
	private static final String PASSWORD = "Soy-Polizona-53";

	private Connection connection = null;

	/**
	 * Constructor that opens the connection to the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DataAccessObject() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function that closes the connection with the database
	 */
	public void closeConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				// ERROR: there is no connection
				throw new DAOInitializationException("DAO was previously closed.");
			} else {
				// OK: close the connection
				connection.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to create a preparedStatement
	 * 
	 * @param sql SQL query to execute
	 * @return Precompiled query
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException, DAOInitializationException {
		if (connection == null || connection.isClosed()) {
			// ERROR: there is no connection
			throw new DAOInitializationException("DAO was previously closed");

		} else {
			// OK: the connection is open, return the prepared statement
			return connection.prepareStatement(sql);
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to close the preparedStatement
	 * 
	 * @param ps The preparedStatement object to close
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public void closePreparedStatement(PreparedStatement ps) throws SQLException, DAOInitializationException {
		if (connection == null || connection.isClosed()) {
			// ERROR: there is no connection
			throw new DAOInitializationException("DAO was previously closed.");
		} else if (ps != null && !ps.isClosed()) {
			// OK: close prepared statement
			ps.close();
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to close the resultSet
	 * 
	 * @param rs The resultSet object to close
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public void closeResultSet(ResultSet rs) throws SQLException, DAOInitializationException {
		if (connection == null || connection.isClosed()) {
			// ERROR: there is no connection
			throw new DAOInitializationException("DAO was previously closed.");
		} else if (rs != null && !rs.isClosed()) {
			// OK: close result set
			rs.close();
		}
	}

	/*
	 **************************************************************************
	 * FUNCTION
	 **************************************************************************/
	/**
	 * Function to get the database name
	 * 
	 * @return String
	 */
	public static String getDatabase() {
		return DATABASE;
	}

}
