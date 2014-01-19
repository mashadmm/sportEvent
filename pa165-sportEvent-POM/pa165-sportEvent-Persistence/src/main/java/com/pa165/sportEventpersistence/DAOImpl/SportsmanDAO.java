/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAOImpl;


import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.sportEventpersistence.entities.Sportsman;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Maria
 */

@Repository("sportsmanDAO")
public class SportsmanDAO extends BaseDAO<Sportsman> {
    
    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public SportsmanDAO() {
            super(Sportsman.class);
    }
    
    public boolean validateEntity(Sportsman entity)throws IllegalArgumentPersistenceException{
        if ((entity == null)||(entity.getName() == null)||(entity.getLastname()==null)||entity.getDateOfBirth()==null) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Sportsman) call with Null argument");
        }
        if ((entity.getName() == "")||(entity.getLastname()== "")) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateEntity (class Sportsman) call with Null argument");
        }
        
        return true;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Sportsman> findByLastname(String lastname) throws IllegalArgumentPersistenceException {
        if (lastname == null) {
            throw new IllegalArgumentPersistenceException("Method SportsmanDAO.findByLastname call with Null argument");
        }
        Query q = getEntityManager().createNamedQuery("sportsman.findByLastname");
        q.setParameter("lastname", lastname);
        List<Sportsman> list = (List<Sportsman>) q.getResultList();
        return list;
    }
    
    public Sportsman getByLogin(String login) throws IllegalArgumentPersistenceException {
        if(login == null){
          throw new IllegalArgumentPersistenceException("Method SportsmanDAOImpl.get call with Null argument name");
        }
        List<Sportsman> result;
        result = em.createQuery( "from Sportsman s where s.userName=:login" ).setParameter("login",login).getResultList();
 
        if (!result.isEmpty()) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

}