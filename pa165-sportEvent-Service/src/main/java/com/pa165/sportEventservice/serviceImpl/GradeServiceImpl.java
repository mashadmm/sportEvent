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
import com.pa165.sportEventservice.service.GradeService;
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

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

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

      public void putGrade(SportsmanDTO sportsman, EventDTO event, int eventgrade) throws ServiceFailureException{
        if (sportsman == null || sportsman.getSportsmanId() == null) {
            throw new IllegalArgumentException("sportsman is null in sportsmanServiceImpl.putGrade.");
        }

        if (event == null || event.getEventId() == null) {
            throw new IllegalArgumentException("event is null in sportsmanServiceImpl.putGrade.");
        }
       
        Grade grade = gradeDAO.findById(sportsman.getSportsmanId(), event.getEventId());
        grade.setGrade(eventgrade);
        gradeDAO.persist(grade);
        

    }
    
    public int getPlace(EventDTO event, SportsmanDTO sportsman) throws ServiceFailureException{
        
        if (sportsman == null || sportsman.getSportsmanId() == null) {
            throw new IllegalArgumentException("sportsman is null in sportsmanServiceImpl.getPlace.");
        }

        if (event == null || event.getEventId() == null) {
            throw new IllegalArgumentException("event is null in sportsmanServiceImpl.getPlace.");
        }
        
        int tempplace = 0;
        List <Grade> results = new ArrayList<Grade>();
        results = gradeDAO.findByEvent(event.getEventId());
        for (Grade result : results) {
            tempplace = tempplace+1;
            if ( result.getSportsman().getSportsmanId()== sportsman.getSportsmanId() ){
                    if (result.getGrade()==0){
                        return 0;
                    } else {break;}
            }
            
        }
        
        return tempplace;
    }
    
    
   
}