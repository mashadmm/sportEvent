/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAOImpl;

import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.sportEventpersistence.entities.Sport;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maria
 */

@Repository("sportDAO")
public class SportDAO extends BaseDAO<Sport> {
    
    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public SportDAO() {
            super(Sport.class);
    }
    
    public boolean validateEntity(Sport entity)throws IllegalArgumentPersistenceException{
        if ((entity == null)||(entity.getName() == null)) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Sport) call with Null argument");
        }
        if ((entity.getName() == "")) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Sport) call with Null argument");
        }
        
        return true;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Sport> findByName(String name) throws IllegalArgumentPersistenceException {
        if (name == null) {
            throw new IllegalArgumentPersistenceException("Method SportDAO.findByName call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("sport.findByName");
        q.setParameter("name", name);
        List<Sport> list = (List<Sport>) q.getResultList();
        return list;
    }

}