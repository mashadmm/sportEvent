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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Maria
 */
@WebService
public class SportEventWSImpl implements SportEventWS {

    //ApplicationContext context;
    @SpringBean
    private SportsmanService sportsmanManager;
    @SpringBean
    private GradeService gradeManager;
    @SpringBean
    private EventService eventManager;

    public SportEventWSImpl() {
//        context = new ClassPathXmlApplicationContext("spring-context.xml");
//        sportsmanManager = (SportsmanService) context.getBean("sportService");
//        gradeManager = (GradeService) context.getBean("sportsmanEventService");
//        eventManager = (EventService) context.getBean("eventService");
    }

    // Sportsman
    @WebMethod
    @Override
    public List<SportsmanDTO> sportsmanManagerfindAll() {
        try {
            return sportsmanManager.getAll();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            return sportsmanManager.findById(sportsmanid);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Event
    @WebMethod
    @Override
    public List<EventDTO> eventManagerfindAll() {
        try {
            return eventManager.getAll();
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            return eventManager.findById(eventid);
    }

    // Grade
    
    @WebMethod
    @Override
    public void gradeManagerGetPlace(EventDTO eventDTO, SportsmanDTO sportsmanDTO) {
        try {
            gradeManager.getPlace(eventDTO,sportsmanDTO);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod
    @Override
    public void gradeManagerPutGrade(EventDTO eventDTO, SportsmanDTO sportsmanDTO, int grade) {
        try {
            gradeManager.putGrade(sportsmanDTO, eventDTO, grade);
        } catch (ServiceFailureException ex) {
            Logger.getLogger(SportEventWSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}