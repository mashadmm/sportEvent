package com.pa165.sportEventservice.serviceImpl;


import com.pa165.sportEventpersistence.DAOImpl.EventDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportDAO;
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventpersistence.entities.Event;
import com.pa165.sportEventpersistence.entities.Sport;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.SportDTO;
import com.pa165.sportEventservice.service.SportService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Maria
 */

@Service("sportService")
public class SportServiceImpl implements SportService {

    @Resource(name = "sportDAO")
    private SportDAO sportDAO;
    @Resource(name = "eventDAO")
    private EventDAO eventDAO;
    
    @Autowired
    private Mapper mapper;

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public SportDAO getSportDAO() {
        return sportDAO;
    }

    public void setSportDAO(SportDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

   
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public SportDTO add(SportDTO sport) throws ServiceFailureException {
        if (sport == null) {
            throw new IllegalArgumentException("sport is null in SportServiceImpl.add ");
        }

        Sport sportToAdd = mapper.map(sport, Sport.class);

        sportDAO.persist(sportToAdd);
        return mapper.map(sportToAdd, SportDTO.class);

    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void remove(SportDTO sport) throws ServiceFailureException {
        if (sport == null) {
            throw new IllegalArgumentException("sport is null in  sportServiceImpl.remove ");
        }

        if (sport.getSportId()== null) {
            throw new IllegalArgumentException("sport.Id is null in sportServiceImpl.remove ");
        }
        
        Sport toRemove = sportDAO.findById(sport.getSportId());
        if (toRemove== null) {
            throw new IllegalArgumentException("not exist object in sportServiceImpl.remove ");
        }
             sportDAO.remove(toRemove);
    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public SportDTO edit(SportDTO sport) throws ServiceFailureException {
        if (sport == null) {
            throw new IllegalArgumentException("sport is null in  sportServiceImpl.edit ");
        }
        if (sport.getSportId() == null) {
            throw new IllegalArgumentException("sport.Id is null in sportServiceImpl.edit ");
        }
        Sport toModify = mapper.map(sport, Sport.class);

        sportDAO.edit(toModify);

        return mapper.map(toModify, SportDTO.class);
    }

   
    @Transactional(readOnly = true)
    public SportDTO findById(Long id) throws ServiceFailureException {
        if (id == null) {
            throw new IllegalArgumentException("ID is null.");
        }

        Sport sport = sportDAO.findById(id);
        SportDTO sportDTO = null;
        if (sport != null) {
            sportDTO = mapper.map(sport, SportDTO.class);
        }
        return sportDTO;

    }

    
    @Transactional(readOnly = true)
    public List<SportDTO> getAll() throws ServiceFailureException{
        List<Sport> sports = sportDAO.findAll();
        List<SportDTO> result = new ArrayList<SportDTO>();
        for (Sport sport : sports) {
            SportDTO tempsport = mapper.map(sport, SportDTO.class);
            result.add(tempsport);
        }

        return result;
    }

    
   
    
       
    @Transactional(readOnly = true)
    public List<SportDTO> findByName(String name) throws ServiceFailureException {
        if (name == null) {
            throw new IllegalArgumentException("lastname is null in sportsmanServiceImpl.findByLastname.");
        }


        List<Sport> sports = sportDAO.findByName(name);
        List<SportDTO> result = new ArrayList<SportDTO>();
        for (Sport sport : sports) {
            SportDTO tempsport = mapper.map(sport, SportDTO.class);
            result.add(tempsport);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<EventDTO> getEvents(SportDTO sport) throws ServiceFailureException {
        if (sport == null) {
            throw new IllegalArgumentException("sport is null in sportServiceImpl.getEvents.");
        }

        Sport sportWithEvents = sportDAO.findById(sport.getSportId());
        List<EventDTO> result = new ArrayList<EventDTO>();
        for (Event event : sportWithEvents.getEvents()) {
            EventDTO tempevent = mapper.map(event, EventDTO.class);
            result.add(tempevent);
        }

        return result;
    }
    
  
    
    
   
}
