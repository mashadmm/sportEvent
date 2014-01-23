package com.pa165.sportEventservice.serviceImpl;


import com.pa165.sportEventpersistence.DAOImpl.EventDAO;
import com.pa165.sportEventpersistence.DAOImpl.GradeDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportsmanDAO;
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventpersistence.entities.Event;
import com.pa165.sportEventpersistence.entities.Grade;
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
import com.pa165.sportEventservice.service.SportsmanService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;


/**
 *
 * @author Maria
 */

@Service("sportsmanService")
public class SportsmanServiceImpl implements SportsmanService {

    @Resource(name = "sportsmanDAO")
    private SportsmanDAO sportsmanDAO;
    @Resource(name = "eventDAO")
    private EventDAO eventDAO;
    @Resource(name = "gradeDAO")
    private GradeDAO gradeDAO;
    @Autowired
    private Mapper mapper;

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public SportsmanDAO getSportsmanDAO() {
        return sportsmanDAO;
    }

    public void setSportsmanDAO(SportsmanDAO sportsmanDAO) {
        this.sportsmanDAO = sportsmanDAO;
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
    @Override
    public SportsmanDTO add(SportsmanDTO sportsman) throws ServiceFailureException {
        if (sportsman == null) {
            throw new IllegalArgumentException("sportsman is null in SportsmanServiceImpl.add ");
        }

        Sportsman sportsmanToAdd = mapper.map(sportsman, Sportsman.class);

        sportsmanDAO.persist(sportsmanToAdd);
        return mapper.map(sportsmanToAdd, SportsmanDTO.class);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void remove(SportsmanDTO sportsman) throws ServiceFailureException {
        if (sportsman == null) {
            throw new IllegalArgumentException("sportsman is null in  sportsmanServiceImpl.remove ");
        }

        if (sportsman.getSportsmanId()== null) {
            throw new IllegalArgumentException("sportsman.Id is null in sportsmanServiceImpl.remove ");
        }
        Sportsman toRemove = sportsmanDAO.findById(sportsman.getSportsmanId());
        if (toRemove== null) {
            throw new IllegalArgumentException("not exist object in eventServiceImpl.remove ");
        }
                
        sportsmanDAO.remove(toRemove);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public SportsmanDTO edit(SportsmanDTO sportsman) throws ServiceFailureException {
        if (sportsman == null) {
            throw new IllegalArgumentException("sportsman is null in  sportsmanServiceImpl.edit ");
        }
        if (sportsman.getSportsmanId() == null) {
            throw new IllegalArgumentException("sportsman.Id is null in sportsmanServiceImpl.edit ");
        }
        Sportsman toModify = mapper.map(sportsman, Sportsman.class);

        sportsmanDAO.edit(toModify);

        return mapper.map(toModify, SportsmanDTO.class);
    }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public SportsmanDTO findById(Long id) throws ServiceFailureException {
        if (id == null) {
            throw new IllegalArgumentException("ID is null.");
        }

        Sportsman sportsman = sportsmanDAO.findById(id);
        SportsmanDTO sportsmanDTO = null;
        if (sportsman != null) {
            sportsmanDTO = mapper.map(sportsman, SportsmanDTO.class);
        }
        return sportsmanDTO;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public List<SportsmanDTO> getAll() throws ServiceFailureException{
        List<Sportsman> sportsmans = sportsmanDAO.findAll();
        List<SportsmanDTO> result = new ArrayList<SportsmanDTO>();
        for (Sportsman sportsman : sportsmans) {
            SportsmanDTO tempsportsman = mapper.map(sportsman, SportsmanDTO.class);
            result.add(tempsportsman);
        }

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public SportsmanDTO registerToEvent(SportsmanDTO sportsman, EventDTO event) throws ServiceFailureException {
        if (sportsman == null || sportsman.getSportsmanId() == null) {
            throw new IllegalArgumentException("sportsman is null in sportsmanServiceImpl.registerToEvent.");
        }

        if (event == null || event.getEventId() == null) {
            throw new IllegalArgumentException("event is null in sportsmanServiceImpl.registerToEvent.");
        }
        List<Grade> grades = gradeDAO.findBySportsman(sportsman.getSportsmanId());
        for (Grade grade : grades) {
            if (event.getEventId().equals(grade.getEvent().getEventId())){
                throw new ServiceFailureException("event is already registered for this sportsman in sportsmanServiceImpl.registerToEvent.");
            }
        }
        
        Date currentdate = new Date();
        Long id = event.getEventId();
        Event eventForRegistry = eventDAO.findById(event.getEventId());
        if (eventForRegistry.getDateOfEvent().before(currentdate)){
             throw new IllegalArgumentException("current date is after date of event in sportsmanServiceImpl.registerToEvent.");
        }

        Sportsman sportsmanForRegistry = sportsmanDAO.findById(sportsman.getSportsmanId());
        
        Grade registrationEntry = new Grade();
        int gradeByDeffault = 0;
        registrationEntry.setGrade(gradeByDeffault);
        registrationEntry.setSportsman(sportsmanForRegistry);
        registrationEntry.setEvent(eventForRegistry);
        sportsmanForRegistry.getResults().add(registrationEntry);

        sportsmanDAO.persist(sportsmanForRegistry);

        return mapper.map(sportsmanForRegistry, SportsmanDTO.class);


    }

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public List<SportsmanDTO> findByLastname(String lastname) throws ServiceFailureException {
        if (lastname == null) {
            throw new IllegalArgumentException("lastname is null in sportsmanServiceImpl.findByLastname.");
        }


        List<Sportsman> sportsmans = sportsmanDAO.findByLastname(lastname);
        List<SportsmanDTO> result = new ArrayList<SportsmanDTO>();
        for (Sportsman sportsman : sportsmans) {
            SportsmanDTO tempsportsman = mapper.map(sportsman, SportsmanDTO.class);
            result.add(tempsportsman);
        }

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public Map<EventDTO, GradeDTO> getEventsWithGrades(SportsmanDTO sportsman) throws ServiceFailureException {
        List<Grade> grades = gradeDAO.findBySportsman(sportsman.getSportsmanId());
        HashMap<EventDTO,GradeDTO> cache = new HashMap<EventDTO,GradeDTO>();
        for (Grade grade : grades) {
            Event eventByGrade = eventDAO.findById(grade.getEvent().getEventId());
            EventDTO event = mapper.map(eventByGrade, EventDTO.class);
            GradeDTO gradeDto = mapper.map(grade, GradeDTO.class);
            cache.put(event, gradeDto);
        }

        return cache;
    }
    
    @Transactional(readOnly=true)
    public SportsmanDTO getByLogin(String login) throws ServiceFailureException {
        if (login == null) {
            throw new IllegalArgumentException("name is null in SportsmanServiceImpl.getByLogin.");
        }

        Sportsman sportsman = sportsmanDAO.getByLogin(login);
        SportsmanDTO result = mapper.map(sportsman, SportsmanDTO.class);
		
        return result;	
        }	
        
    
    
   
}
