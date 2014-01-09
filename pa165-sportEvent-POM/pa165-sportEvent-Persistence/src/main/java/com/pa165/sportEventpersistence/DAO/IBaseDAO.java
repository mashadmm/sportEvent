/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAO;


import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import java.util.List;


/**
 *
 * @author Maria
 * 
 * common interface for entities
 */

public interface IBaseDAO <T> {
    
    public void remove(T entity) throws IllegalArgumentPersistenceException;
    public T edit(T entity) throws IllegalArgumentPersistenceException;
    public T persist(T entity)throws IllegalArgumentPersistenceException;
    public List<T> findAll();
    public T findById(Long id) throws IllegalArgumentPersistenceException;
}
