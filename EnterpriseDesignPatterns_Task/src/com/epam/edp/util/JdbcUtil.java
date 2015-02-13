package com.epam.edp.util;


import java.sql.*;

import org.apache.log4j.Logger;

import com.epam.edp.exception.DAOException;

public class JdbcUtil {
    private final static Logger log = Logger.getLogger(JdbcUtil.class);

    public static void closeConnection(Connection connection) throws DAOException {
	if (connection != null) {
		try {
                    connection.close();
		} catch (SQLException e) {
                    throw new DAOException(e.getMessage());
		}
	}
    }
    
    public static void closeStatement(Statement statement) throws DAOException {
	if (statement != null) {
		try {
                    statement.close();
		} catch (SQLException e) {
                    throw new DAOException(e.getMessage());
		}
	}
    }
    
    public static void closeResultSet(ResultSet resultSet) throws DAOException {
	if (resultSet != null) {
		try {
                    resultSet.close();
		} catch (SQLException e) {
                    throw new DAOException(e.getMessage());
		}
	}
    }
    
        public void closePreparedStatement(PreparedStatement st) throws DAOException {
        if (st!=null){
            try {
                st.close();
            } catch (SQLException ex) {
                throw new DAOException(ex.getMessage());
            }
        }
    }
}


