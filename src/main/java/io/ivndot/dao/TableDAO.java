package io.ivndot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.ivndot.exception.DAOInitializationException;

public class TableDAO extends DataAccessObject {

	public TableDAO() throws ClassNotFoundException, SQLException {
		// initialize connection
		super();
	}

	/**
	 * Function to get the names of the tables in the database
	 * 
	 * @return List of table names
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public List<String> getTableNames() throws SQLException, DAOInitializationException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> tableNamesList = new ArrayList<String>();

		String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

		try {
			ps = prepareStatement(sql);
			ps.setString(1, getDatabase());

			rs = ps.executeQuery();

			while (rs.next()) {
				// add table name to the list
				tableNamesList.add(rs.getString(1));
			}

			return tableNamesList;

		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	}

}
