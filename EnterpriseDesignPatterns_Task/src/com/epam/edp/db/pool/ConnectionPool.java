package com.epam.edp.db.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.epam.edp.exception.DAOException;
import com.epam.edp.exception.ResourceCreationException;
import com.epam.edp.util.JdbcUtil;

public class ConnectionPool {

    private static Logger log = Logger.getLogger("ConnectionPool");
    private final static int MAX_RESOURCES = 5;
    private final Semaphore sem = new Semaphore(MAX_RESOURCES, true);
    
    private  Queue<Connection> freeConnections;
    private  Queue<Connection> busyConnections;
    private String driverClassName;
    private String url;
    private String username;
    private String password;


    public ConnectionPool(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
        freeConnections=new LinkedList<Connection>();
        busyConnections=new LinkedList<Connection>();
        for (int i = 0; i <MAX_RESOURCES; i++) {
            try {
                freeConnections.add(DriverManager.getConnection(
                        url,
                        username,
                        password));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void init() throws DAOException {
        try {
            Class.forName(driverClassName);
            for (int i = 0; i <MAX_RESOURCES; i++) {
                try {
                    freeConnections.add(DriverManager.getConnection(
                            url,
                            username,
                            password));
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }  catch (ClassNotFoundException ex) {
            log.error("ERROR : class is not found");
        }
    }

    

    /**
     * @return connection from the pool
     * @param maxWaitMillis
     */
    public Connection getConnection(long maxWaitMillis) throws ResourceCreationException {
        try {
            // First, get permission to take or create a resource
            sem.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
        }

        // Then, actually take one if available...
        Connection res = freeConnections.poll();
        if (res == null) {
            throw new ResourceCreationException("ERROR : message pool is empty");
        }
        busyConnections.add(res);
        return res;
    }

    /**
     * return the connection to the pool
     *
     * @param res
     */
    public void releaseConnection(Connection res) {
        busyConnections.remove(res);
        if (res!=null){
            freeConnections.add(res);            
        }
        sem.release();
    }

    /**
     * release the connection pool
     */
    private void closeConnection(Queue<Connection> connections) throws DAOException{
        Connection connection = null;        
        while((connection=connections.poll())!=null){
            JdbcUtil.closeConnection(connection);
        }
    }
    
    public void dispose() throws DAOException{
        closeConnection(freeConnections);
        closeConnection(busyConnections);
    }
  
}


