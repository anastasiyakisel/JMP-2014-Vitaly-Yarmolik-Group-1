package com.epam.edp.dao;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.edp.cache.CustomCache;
import com.epam.edp.constants.DBConstants;
import com.epam.edp.db.pool.ConnectionPool;
import com.epam.edp.exception.DAOException;
import com.epam.edp.exception.ResourceCreationException;
import com.epam.edp.model.CustomObject;
import com.epam.edp.util.JdbcUtil;


public class CustomDAO extends JdbcUtil implements IDAO<CustomObject>{

	 private final static Logger logger=Logger.getLogger(CustomDAO.class);

	 private static final int MAX_CONNECTION_WAIT=1000;
	 
	 private ConnectionPool connectionPool=new ConnectionPool(DBConstants.DRIVER_NAME, 
			 DBConstants.DB_URL, DBConstants.DB_USERNAME, DBConstants.DB_PASSWORD);
	 
	 private static final String SQL_GET_OBJ_BY_ID="SELECT ID, NAME FROM CUSTOM_OBJECT WHERE ID=?";
	 
	 private static final String INSERT_OBJ_INTO_TABLE="INSERT INTO CUSTOM_OBJECT(ID, NAME) VALUES (?,?)";
	 
	 private static final String SQL_DELETE_OBJ ="DELETE FROM CUSTOM_OBJECT WHERE ID=?";
	 
	 private static final String UPDATE_OBJ="UPDATE OBJ SET NAME=? WHERE ID=?";

	@Override
	public CustomObject create(CustomObject obj) throws DAOException {
		Connection connection = null;
        PreparedStatement st = null;
        try {
            connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(INSERT_OBJ_INTO_TABLE);
            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());    
            st.executeUpdate();
        } catch (ResourceCreationException ex) {
            logger.error(ex);
        }catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally{
            closePreparedStatement(st);
            connectionPool.releaseConnection(connection);
        }
        return obj;
	}

	@Override
	public void remove(Integer id) throws DAOException{
		Connection cn=null;
        PreparedStatement st=null;
        try {
            cn=connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st =cn.prepareStatement(SQL_DELETE_OBJ);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (ResourceCreationException ex) {
            logger.error(ex);
        }catch (SQLException ex) {
           throw new DAOException(ex.getMessage());
        } finally{
            closePreparedStatement(st);
            connectionPool.releaseConnection(cn);
        }	
	}

	@Override
	public void update(CustomObject obj) throws DAOException{
		Connection cn=null;
        PreparedStatement st=null;
        try {
            cn=connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st = cn.prepareStatement(UPDATE_OBJ);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());           
            st.executeUpdate();
        } catch (ResourceCreationException ex) {
            logger.equals(ex);
        }catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally{
            closeStatement(st);
            connectionPool.releaseConnection(cn);
        }
	}

	@Override
	public CustomObject get(Integer id) throws DAOException {
		CustomObject obj = new CustomObject();
        Connection cn = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            cn=connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=cn.prepareStatement(SQL_GET_OBJ_BY_ID);
            st.setInt(1, id);
            rs=st.executeQuery();
            if (rs.next()){
                obj=fillCustomObject(rs);
            }
        } catch (ResourceCreationException ex) {
            logger.error(ex);
        }catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally{
            if (rs!=null){
                closeResultSet(rs);    
            }
            closePreparedStatement(st);
            connectionPool.releaseConnection(cn);
        }
        return obj;
	}
	
	private CustomObject fillCustomObject(ResultSet rs){
		CustomObject obj = new CustomObject();
		try{
			obj.setId(rs.getInt(1));
			obj.setName(rs.getString(2));
		} catch (InvalidParameterException ex){
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	 
	 
	 
}
