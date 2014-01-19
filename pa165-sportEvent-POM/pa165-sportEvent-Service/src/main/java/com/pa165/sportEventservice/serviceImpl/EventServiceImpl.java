package com.pa165.sportEventservice.serviceImpl;


import com.pa165.sportEventpersistence.DAOImpl.GradeDAO;
import com.pa165.sportEventpersistence.DAOImpl.EventDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportsmanDAO;
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventpersistence.entities.Grade;
import com.pa165.sportEventpersistence.entities.Event;
import com.pa165.sportEventpersistence.entities.Sportsman;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.pa165.sportEventservice.service.EventService;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;


/**
 *
 * @author Maria
 */

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Resource(name = "eventDAO")
    private EventDAO eventDAO;
    @Resource(name = "gradeDAO")
    private GradeDAO gradeDAO;
    @Resource(name = "sportsmanDAO")
    private SportsmanDAO sportsmanDAO;
    
    @Autowired
    private Mapper mapper;

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public GradeDAO getGradeDAO() {
        return gradeDAO;
    }

    public void setGradeDAO(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public EventDTO add(EventDTO event) throws ServiceFailureException {
        if (event == null) {
            throw new IllegalArgumentException("event is null in EventServiceImpl.add ");
        }

        Event eventToAdd = mapper.map(event, Event.class);

        eventDAO.persist(eventToAdd);
        return mapper.map(eventToAdd, EventDTO.class);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void remove(EventDTO event) throws ServiceFailureException {
        if (event == null) {
            throw new IllegalArgumentException("event is null in  eventServiceImpl.remove ");
        }

        if (event.getEventId()== null) {
            throw new IllegalArgumentException("event.Id is null in eventServiceImpl.remove ");
        }
        Event toRemove = eventDAO.findById(event.getEventId());
        if (toRemove== null) {
            throw new IllegalArgumentException("not exist object in eventServiceImpl.remove ");
        }

        eventDAO.remove(toRemove);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public EventDTO edit(EventDTO event) throws ServiceFailureException {
        if (event == null) {
            throw new IllegalArgumentException("event is null in  eventServiceImpl.edit ");
        }
        if (event.getEventId() == null) {
            throw new IllegalArgumentException("event.Id is null in eventServiceImpl.edit ");
        }
        Event toModify = mapper.map(event, Event.class);

        eventDAO.edit(toModify);

        return mapper.map(toModify, EventDTO.class);
    }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    public EventDTO findById(Long id) throws ServiceFailureException {
        if (id == null) {
            throw new IllegalArgumentException("ID is null.");
        }

        Event event = eventDAO.findById(id);
        EventDTO eventDTO = null;
        if (event != null) {
            eventDTO = mapper.map(event, EventDTO.class);
        }
        return eventDTO;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    public List<EventDTO> getAll() throws ServiceFailureException{
        List<Event> events = eventDAO.findAll();
        List<EventDTO> result = new ArrayList<EventDTO>();
        for (Event event : events) {
            EventDTO tempevent = mapper.map(event, EventDTO.class);
            result.add(tempevent);
        }

        return result;
    }

    
       
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") 
    @Transactional(readOnly = true)
    public List<EventDTO> findByName(String name) throws ServiceFailureException {
        if (name == null) {
            throw new IllegalArgumentException("name is null in eventServiceImpl.findByName.");
        }


        List<Event> events = eventDAO.findByName(name);
        List<EventDTO> result = new ArrayList<EventDTO>();
        for (Event event : events) {
            EventDTO tempevent = mapper.map(event, EventDTO.class);
            result.add(tempevent);
        }

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public List<EventDTO> findByDates(Date startDate, Date endDate) throws ServiceFailureException {
        if (startDate == null) {
            throw new IllegalArgumentException("startDate is null in eventServiceImpl.findByDates.");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("endDate is null in eventServiceImpl.findByDates.");
        }
        List<Event> events = eventDAO.findByDates(startDate, endDate);
        List<EventDTO> result = new ArrayList<EventDTO>();
        for (Event event : events) {
            EventDTO tempevent = mapper.map(event, EventDTO.class);
            result.add(tempevent);
        }

        return result;
        
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public List<SportsmanDTO> getSportsmans(EventDTO event) throws ServiceFailureException {
        List<Grade> grades = gradeDAO.findByEvent(event.getEventId());
        List<SportsmanDTO> result = new ArrayList<SportsmanDTO>();
        for (Grade grade : grades) {
            Sportsman sportsmanByGrade = sportsmanDAO.findById(grade.getSportsman().getSportsmanId());
            SportsmanDTO sportsman = mapper.map(sportsmanByGrade, SportsmanDTO.class);
            result.add(sportsman);
        }

        return result;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public Map<SportsmanDTO, GradeDTO> getSportsmansWithGrades(EventDTO event) throws ServiceFailureException {
        List<Grade> grades = gradeDAO.findByEvent(event.getEventId());
        Map<SportsmanDTO,GradeDTO> cache = new LinkedHashMap<SportsmanDTO,GradeDTO>();
        for (Grade grade : grades) {
            Sportsman sportsmanByGrade = sportsmanDAO.findById(grade.getSportsman().getSportsmanId());
            SportsmanDTO sportsman = mapper.map(sportsmanByGrade, SportsmanDTO.class);
            GradeDTO gradeDto = mapper.map(grade, GradeDTO.class);
            cache.put(sportsman, gradeDto);
        }

        return cache;
    }

    
  
    
    
   
}
