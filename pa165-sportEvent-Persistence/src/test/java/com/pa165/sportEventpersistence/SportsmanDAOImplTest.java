/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence;


import com.pa165.sportEventpersistence.DAOImpl.EventDAO;
import com.pa165.sportEventpersistence.DAOImpl.GradeDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportsmanDAO;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.sportEventpersistence.entities.Event;
import com.pa165.sportEventpersistence.entities.Grade;
import com.pa165.sportEventpersistence.entities.Sport;
import com.pa165.sportEventpersistence.entities.Sportsman;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Maria
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
public class SportsmanDAOImplTest {

    @Autowired
    private ApplicationContext context;
    private SportDAO sportDao;
    private SportsmanDAO sportsmanDao;
    private EventDAO eventDao;
    private GradeDAO gradeDao;
    

    private void setSportsmanDAO(SportsmanDAO dao) {
        this.sportsmanDao = dao;
    }
     private void setSportDAO(SportDAO dao) {
        this.sportDao = dao;
    }
      private void setEventDAO(EventDAO dao) {
        this.eventDao = dao;
    }
       private void setGradeDAO(GradeDAO dao) {
        this.gradeDao = dao;
    }

   

    @Before
    public void setUp() {
        setSportsmanDAO((SportsmanDAO) context.getBean("sportsmanDAO"));
        setSportDAO((SportDAO) context.getBean("sportDAO"));
        setEventDAO((EventDAO) context.getBean("eventDAO"));
        setGradeDAO((GradeDAO) context.getBean("gradeDAO"));
       
    }

    @After
    public void tearDown() {
        setSportsmanDAO(null);
        context = null;

    }

    @Test(expected = IllegalArgumentPersistenceException.class)
    public void persistWithException() throws IllegalArgumentPersistenceException{
        Sportsman sportsman = new Sportsman();
        //check IllegalArguments
        sportsmanDao.persist(sportsman);
           
    }
    
    @Test
    public void persist() throws IllegalArgumentPersistenceException, ParseException {
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Jon");
        sportsman.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);
        Sportsman toCompare = null;
        try {
            sportsmanDao.persist(sportsman);
            toCompare = sportsmanDao.findById(sportsman.getSportsmanId());
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(sportsman, toCompare);
    }

    @Test
    public void findById() throws IllegalArgumentPersistenceException, ParseException {
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Jon");
        sportsman.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString1 = "31-08-1982";
	java.util.Date date1 = sdf.parse(dateInString1);
        sportsman.setDateOfBirth(date1);
        
        try {
            sportsmanDao.persist(sportsman);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Sport sport = new Sport();
        sport.setName("Swimming");
        
        try {
           sportDao.persist(sport);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Event event = new Event();
        event.setName("World Swimming");
        event.setSport(sport);
        String dateInString2 = "01-05-2014";
	java.util.Date date2 = sdf.parse(dateInString2);
        event.setDateOfEvent(date2);
        
         try {
           eventDao.persist(event);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Grade grade = new Grade();
        grade.setSportsman(sportsman);
        grade.setEvent(event);
        
        sportsman.getResults().add(grade);
        
        Sportsman toCompare = null;
        try {
            sportsmanDao.persist(sportsman);
            toCompare = sportsmanDao.findById(sportsman.getSportsmanId());
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(sportsman, toCompare);
    }

    
     @Test
    public void findEventByAssociativeRelationshipForSportsman() throws IllegalArgumentPersistenceException, ParseException {
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Jon");
        sportsman.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString1 = "31-08-1982";
	java.util.Date date1 = sdf.parse(dateInString1);
        sportsman.setDateOfBirth(date1);
        
        try {
            sportsmanDao.persist(sportsman);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Sport sport = new Sport();
        sport.setName("Swimming");
        
        try {
           sportDao.persist(sport);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Event event = new Event();
        event.setName("World Swimming");
        event.setSport(sport);
        String dateInString2 = "01-05-2014";
	java.util.Date date2 = sdf.parse(dateInString2);
        event.setDateOfEvent(date2);
        
         try {
           eventDao.persist(event);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Grade grade = new Grade();
        grade.setSportsman(sportsman);
        grade.setEvent(event);
        
        sportsman.getResults().add(grade);
        
        Event toCompare = null;
        List <Grade> resultsToCompare = new ArrayList<Grade>();
        try {
            sportsmanDao.persist(sportsman);
            resultsToCompare = (sportsmanDao.findById(sportsman.getSportsmanId())).getResults();
            toCompare = (resultsToCompare.get(0)).getEvent();
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(event, toCompare);
    }
     
     @Test
    public void edit() throws IllegalArgumentPersistenceException, ParseException {
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Jon");
        sportsman.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);
        Sportsman toCompare = null;
        try {
            sportsmanDao.persist(sportsman);
            sportsman.setName("Jack");
            sportsmanDao.edit(sportsman);
            toCompare = sportsmanDao.findById(sportsman.getSportsmanId());
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(sportsman, toCompare);
    }
     
    @Test
    public void findAll() throws ParseException  {
        Sportsman sportsman1 = new Sportsman();
        sportsman1.setName("Jon");
        sportsman1.setLastname("Snow");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy");
	String dateInString1 = "31-08-1982";
	java.util.Date date1 = sdf1.parse(dateInString1);
        sportsman1.setDateOfBirth(date1);

        Sportsman sportsman2 = new Sportsman();
        sportsman2.setName("Jon");
        sportsman2.setLastname("Snow");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
	String dateInString2 = "31-08-1982";
	java.util.Date date2 = sdf2.parse(dateInString2);
        sportsman2.setDateOfBirth(date2);
        try {
            sportsmanDao.persist(sportsman1);
            sportsmanDao.persist(sportsman2);
           
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Sportsman> sportsmans = sportsmanDao.findAll();
        Assert.assertTrue(sportsmans.contains(sportsman1));
        Assert.assertTrue(sportsmans.contains(sportsman2));
        Assert.assertEquals(2, sportsmans.size());
    }

    @Test
    public void remove() throws ParseException  {
        Sportsman sportsman1 = new Sportsman();
        sportsman1.setName("Jon");
        sportsman1.setLastname("Snow");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy");
	String dateInString1 = "31-08-1982";
	java.util.Date date1 = sdf1.parse(dateInString1);
        sportsman1.setDateOfBirth(date1);

        Sportsman sportsman2 = new Sportsman();
        sportsman2.setName("Jorge");
        sportsman2.setLastname("McLee");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
	String dateInString2 = "31-08-1982";
	java.util.Date date2 = sdf2.parse(dateInString2);
        sportsman2.setDateOfBirth(date2);
        try {
            sportsmanDao.persist(sportsman1);
            sportsmanDao.persist(sportsman2);
            sportsmanDao.remove(sportsman1);
           
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Sportsman> sportsmans = sportsmanDao.findAll();
        Assert.assertFalse(sportsmans.contains(sportsman1));
        Assert.assertTrue(sportsmans.contains(sportsman2));
        Assert.assertEquals(1, sportsmans.size());
    }
    

    @Test
    public void findByLastname() throws ParseException, IllegalArgumentPersistenceException  {
        try {
            sportsmanDao.findByLastname(null);
            Assert.fail("No exception");
        } catch (IllegalArgumentPersistenceException ex) {
            Assert.assertNotNull(ex);
        }
        Sportsman sportsman2 = new Sportsman();
        sportsman2.setName("Jorge");
        sportsman2.setLastname("McLee");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
	String dateInString2 = "31-08-1982";
	java.util.Date date2 = sdf2.parse(dateInString2);
        sportsman2.setDateOfBirth(date2);
        try {
            sportsmanDao.persist(sportsman2);
            
        } catch (IllegalArgumentPersistenceException ex) {
            Logger.getLogger(SportsmanDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Sportsman> sportsmans = sportsmanDao.findByLastname("McLee");
        Assert.assertTrue(sportsmans.contains(sportsman2));
        sportsmans = sportsmanDao.findByLastname("McNee");
        Assert.assertEquals(0, sportsmans.size());

    }




}
