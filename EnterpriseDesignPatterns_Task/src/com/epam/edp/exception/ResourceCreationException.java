package com.epam.edp.exception;

/**
 *
 * @author Anastasiya_Kisel
 */
public class ResourceCreationException extends Exception{
    private Exception _hidden;
    
    public ResourceCreationException(String er){
        super(er);
    }
    
    public ResourceCreationException(String er, Exception e){
        super(er);
        _hidden=e;
    }

    public Exception getHiddenException(){
        return _hidden;
    }
}
