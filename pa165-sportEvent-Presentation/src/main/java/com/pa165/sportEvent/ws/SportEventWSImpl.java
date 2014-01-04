/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEvent.ws;

import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.SportDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import com.pa165.sportEventservice.service.EventService;
import com.pa165.sportEventservice.service.SportService;
import com.pa165.sportEventservice.service.GradeService;
import com.pa165.sportEventservice.service.SportsmanService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 *
 * @author Maria
 */
@WebService
public class SportEventWSImpl extends SpringBeanAutowiringSupport implements SportEventWS  {

    ApplicationContext context;
    private SportsmanService sportsmanManager;
    private GradeService gradeManager;
    private EventService eventManager;
    private SportService sportManager;

    public SportEventWSImpl() {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
        sportsmanManager = (SportsmanService) context.getBean("sportsmanService");
        gradeManager = (GradeService) context.getBean("gradeService");
        sportManager = (SportService) context.getBean("sportService");
        eventManager = (EventService) context.getBean("eventService");
    }

    // Sportsman
    @WebMethod
    @Override
    public List<SportsmanDTO> sportsmanManagerfindAll() {
        List<SportsmanDTO> list = new ArrayList<SportsmanDTO>();
        try {
            list = sportsmanManager.getAll();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        for(SportsmanDTO sportsman : list){
            sportsman.setResults(null);
        }
        return list;
    }

    @WebMethod
    @Override
    public void sportsmanManagersave(SportsmanDTO sportsman) {
        try {
            sportsmanManager.add(sportsman);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void sportsmanManagerupdate(SportsmanDTO sportsman) {
        try {
            sportsmanManager.edit(sportsman);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void sportsmanManagerdelete(SportsmanDTO sportsman) {
        try {
            sportsmanManager.remove(sportsman);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public SportsmanDTO sportsmanManagerfindById(Long sportsmanid) {
        try {
            SportsmanDTO sportsman = sportsmanManager.findById(sportsmanid);
            sportsman.setResults(null);
            return sportsman;
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Event
    @WebMethod
    @Override
    public List<EventDTO> eventManagerfindAll() {
        List<EventDTO> list = new ArrayList<EventDTO>();
        try {
            list = eventManager.getAll();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        for(EventDTO event : list){
            event.setResults(null);
            event.getSport().setEvents(null);
        }
        return list;
        
    }

    @WebMethod
    @Override
    public void eventManagersave(EventDTO event) {
        try {
            eventManager.add(event);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void eventManagerupdate(EventDTO event) {
        try {
            eventManager.edit(event);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void eventManagerdelete(EventDTO event) {
        try {
            eventManager.remove(event);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public EventDTO eventManagerfindById(Long eventid) throws ServiceFailureException {
            EventDTO event = eventManager.findById(eventid);
            event.getSport().setEvents(null);
            event.setResults(null);
            return event;
    }

    // Grade
    
 
    @WebMethod
    @Override
    public void gradeManagerPutGrade(EventDTO eventDTO, SportsmanDTO sportsmanDTO, int grade) {
        try {
            gradeManager.putGrade(sportsmanDTO.getSportsmanId(), eventDTO.getEventId(), grade);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @WebMethod
    @Override
    public List<GradeDTO> gradeManagerfindAll() {
        List<GradeDTO> list = new ArrayList<GradeDTO>();
        try {
            list = gradeManager.getGrades();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        for(GradeDTO grade : list){
            grade.getSportsman().setResults(null);
            grade.getEvent().setResults(null);
            grade.getEvent().setSport(null);
        }
        return list;
        
    }

    @WebMethod
    @Override
    public void gradeManagersave(GradeDTO grade) {
        try {
            gradeManager.add(grade);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void gradeManagerupdate(GradeDTO grade) {
         try {
            gradeManager.edit(grade);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void gradeManagerdelete(GradeDTO grade) {
         try {
            gradeManager.remove(grade);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public GradeDTO gradeManagerfindById(Long sportsmanid, Long eventid) {
        try {
            GradeDTO grade = gradeManager.findById(sportsmanid, eventid);
            grade.getSportsman().setResults(null);
            grade.getEvent().setResults(null);
            grade.getEvent().setSport(null);
            return grade;
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Sport
    
    @WebMethod
    @Override
    public List<SportDTO> sportManagergetAll(){
        List<SportDTO> list = new ArrayList<SportDTO>();
        try {
            list = sportManager.getAll();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        for(SportDTO sport : list){
            sport.setEvents(null);
        }
        return list;
    }

}