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
import java.util.Date;
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

//    @Test
//    public void getAll() throws ServiceFailureException {
//        Student student1 = new Student();
//        student1.setName("student1");
//        student1.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student1);
//
//        Student student2 = new Student();
//        student2.setName("student2");
//        student2.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student2);
//
//        List<Student> students = studentDao.getAll();
//        Assert.assertTrue(students.contains(student1));
//        Assert.assertTrue(students.contains(student2));
//    }
//
//    @Test
//    public void findByLecture() throws ServiceFailureException {
//        try {
//            studentDao.findByLecture(null);
//            Assert.fail("");
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        }
//
//    }
//
//    @Test
//    public void findByName() throws ServiceFailureException {
//        try {
//            studentDao.findByName(null);
//            Assert.fail("No exception");
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        }
//        Student student1 = new Student();
//        student1.setName("student1");
//        student1.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student1);
//
//        Student student2 = new Student();
//        student2.setName("student2");
//        student2.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student2);
//
//        //normal name
//        List<Student> toCompare = studentDao.findByName(student1.getName());
//        Assert.assertTrue(toCompare.contains(student1));
//        toCompare = studentDao.findByName(student2.getName());
//        Assert.assertTrue(toCompare.contains(student2));
//
//        toCompare = studentDao.findByName("Other name");
//        Assert.assertFalse(toCompare.contains(student1));
//    }
//
//    @Test
//    public void enrollStudentToLecture() throws ServiceFailureException {
//        Student student1 = new Student();
//        student1.setName("student1");
//        student1.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student1);
//
//        try {
//            studentDao.enrollStudentToLecture(null, student1);
//            Assert.fail("No exception");
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        }
//
//        Course course = new Course();
//        course.setName("Course_Student1");
//        course.setLanguage(LanguageSpoken.CZECH);
//        course.setDifficulty(Difficulty.ADVANCED);
//        courseDao.add(course);
//
//        Lector lector = new Lector();
//        lector.setLectures(new ArrayList<Lecture>());
//        lector.setName("lector");
//        lector.setLevelofLanguage(new ArrayList<LevelofLanguage>());
//        lectorDao.add(lector);
//
//        Lecture lecture1 = new Lecture();
//        lecture1.setCourse(course);
//        lecture1.setLector(lector);
//        Calendar calendar = Calendar.getInstance();
//        Date now = calendar.getTime();
//        lecture1.setStartTime(new Timestamp(now.getTime()));
//        lecture1.setEndTime(new Timestamp(now.getTime()));
//        lectureDao.add(lecture1);
//
//        try {
//            studentDao.enrollStudentToLecture(lecture1, null);
//            Assert.fail("No exception");
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        }
//        studentDao.enrollStudentToLecture(lecture1, student1);
//        Assert.assertEquals(1, student1.getLectures().size());
//    }
//
//    @Test
//    public void remove() throws ServiceFailureException {
//        //setting temporary student
//        Student student1 = new Student();
//        student1.setName("student1");
//        student1.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student1);
//
//
//        //--------Testcase : null course
//        try {
//            studentDao.remove(null);
//            Assert.fail();
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        } finally {
//            Assert.assertNotNull(studentDao.get(student1.getId()));
//        }
//
//        //---------Testcase : notmal removal
//        studentDao.remove(student1);
//        Assert.assertNull(studentDao.get(student1.getId()));
//    }
//
//    @Test
//    public void modify() throws ServiceFailureException {
//        //-------Testcase : null object
//        try {
//            courseDao.modify(null);
//            Assert.fail();
//        } catch (IllegalArgumentPersistenceException ex) {
//            Assert.assertNotNull(ex);
//        }
//
//        Student student1 = new Student();
//        student1.setName("student1");
//        student1.setLectures(new ArrayList<Lecture>());
//        studentDao.add(student1);
//
//        student1.setName(null);
//
//        //-------Testcase : null name
//        try {
//            studentDao.modify(student1);
//            Assert.fail();
//        } catch (IllegalArgumentPersistenceException ex) {
//        }
//
//        student1.setName("modify name");
//        student1.setLectures(null);
//        //-------Testcase : null lectures 
//        try {
//            studentDao.modify(student1);
//            Assert.fail();
//        } catch (IllegalArgumentPersistenceException ex) {
//        }
//
//        student1.setName("student100");
//        student1.setLectures(new ArrayList<Lecture>());
//
//        //--------Testcase : normal modofication
//        studentDao.modify(student1);
//        Student toCompare = studentDao.get(student1.getId());
//        Assert.assertEquals(toCompare, student1);
//    }
}
