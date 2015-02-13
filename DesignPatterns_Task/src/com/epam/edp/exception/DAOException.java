package com.epam.edp.exception;

/**
 *
 * @author Anastasiya_Kisel
 */
public class DAOException extends Exception{
    private Exception _hidden;
    
    public DAOException(String er){
        super(er);
    }
    
    public DAOException(String er, Exception e){
        super(er);
        _hidden=e;
    }

    public Exception getHiddenException(){
        return _hidden;
    }
}
