/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.Exceptions;




public class ServiceFailureException extends Exception{
    public ServiceFailureException(String msg) {
        super(msg);
    }
    
    public ServiceFailureException(String msg, Throwable cause) {
        super(msg,cause);
    }
    
}
