/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.Exceptions;

/**
 *
 * @author Elena Medvedeva
 */
public class IllegalArgumentPersistenceException extends ServiceFailureException{
     public IllegalArgumentPersistenceException(String msg) {
        super(msg);
    }
    
    public IllegalArgumentPersistenceException(String msg, Throwable cause) {
        super(msg,cause);
    }    

    
}
