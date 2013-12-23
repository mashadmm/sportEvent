/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.Exceptions;



/**
 *
 * @author Tomas Hruby
 * 
 * Class for represinting Exception that occured during manipulation with entities 
 * of language school (for details see specification of pa165-languageschool).
 * 
 */
public class ServiceFailureException extends Exception{
    public ServiceFailureException(String msg) {
        super(msg);
    }
    
    public ServiceFailureException(String msg, Throwable cause) {
        super(msg,cause);
    }
    
}
