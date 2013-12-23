/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAOImpl;


import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.sportEventpersistence.entities.Event;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maria
 */

@Repository("eventDAO")
public class EventDAO extends BaseDAO<Event> {

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public EventDAO() {
            super(Event.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public boolean validateEntity(Event entity)throws IllegalArgumentPersistenceException{
        if ((entity == null)||(entity.getName() == null)||(entity.getSport()==null)||entity.getDateOfEvent()==null) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Event) call with Null argument");
        }
        if ((entity.getName() == "")) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity call with Null argument");
        }
        
        return true;
    }
     
    public List<Event> findByName(String name) throws IllegalArgumentPersistenceException {
        if ((name == null)||(name == "")) {
            throw new IllegalArgumentPersistenceException("Method EventDAO.findByName call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("event.findByName");
        q.setParameter(1, name);
        List<Event> list = (List<Event>) q.getResultList();
        return list;
    }
    
    public List<Event> findByDates(Date date1, Date date2) throws IllegalArgumentPersistenceException {
        if (date1 == null) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.persist call with Null argument");
        }
        if (date2 == null) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.persist call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("event.findByDates");
        q.setParameter("startDate", date1,TemporalType.DATE);
        q.setParameter("endDate", date2,TemporalType.DATE);
        List<Event> list = (List<Event>) q.getResultList();
        return list;
    }

}