/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAOImpl;

import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.sportEventpersistence.entities.Grade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maria
 */

@Repository("gradeDAO")
public class GradeDAO extends BaseDAO <Grade>{
    
    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradeDAO() {
            super(Grade.class);
    }
    
    
    public Grade findById(Long sportsmanid, Long eventid)  throws IllegalArgumentPersistenceException{
            if ((super.validateID(sportsmanid)||(super.validateID(eventid)))){
            Query q = getEntityManager().createNamedQuery("grade.findByID");
            q.setParameter("id1", sportsmanid);
            q.setParameter("id2", eventid);
            List <Grade> e =  q.getResultList();
            if (e.size()>0){
                return e.get(0);
            } else {return null;}    
        }
        return null;
    }
    
    public boolean validateEntity(Grade entity)throws IllegalArgumentPersistenceException{
        if ((entity == null)||(entity.getSportsman()== null)||(entity.getEvent()==null)) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Result) call with Null argument");
        }
        
        return true;
    }

    public List<Grade> findBySportsman(Long id) throws IllegalArgumentPersistenceException {
        if (id == null) {
            throw new IllegalArgumentPersistenceException("Method ResultDAO.findBySportsman call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("grade.findBySportsman");
        q.setParameter("id", id);
        List<Grade> list = (List<Grade>) q.getResultList();
        return list;
    }
    
    public List<Grade> findByEvent(Long id) throws IllegalArgumentPersistenceException {
        if (id == null) {
            throw new IllegalArgumentPersistenceException("Method ResultDAO.findByEvent call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("grade.findByEvent");
        q.setParameter("id", id);
        List<Grade> list = (List<Grade>) q.getResultList();
        return list;
    }
   
    
}
