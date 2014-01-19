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
import org.springframework.security.access.prepost.PreAuthorize;



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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void putGrade(Long sportsmanId, Long eventId, int eventgrade) throws ServiceFailureException{
        if (sportsmanId == null ) {
            throw new IllegalArgumentException("sportsman is null in sportsmanServiceImpl.putGrade.");
        }

        if (eventId == null ) {
            throw new IllegalArgumentException("event is null in sportsmanServiceImpl.putGrade.");
        }
       
        Grade grade = gradeDAO.findById(sportsmanId, eventId);
        grade.setGrade(eventgrade);
        gradeDAO.persist(grade);
        

    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    public List<GradeDTO> getGrades() throws ServiceFailureException {
                
        List<GradeDTO> result = new ArrayList<GradeDTO>();
        for (Grade grade : gradeDAO.findAll()) {
            GradeDTO tempgrade = mapper.map(grade, GradeDTO.class);
            result.add(tempgrade);
        }

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public GradeDTO add(GradeDTO grade) throws ServiceFailureException {
        if (grade == null) {
            throw new IllegalArgumentException("grade is null in  gradeServiceImpl.add ");
        }

        if (grade.getSportsman().getSportsmanId()== null) {
            throw new IllegalArgumentException("grade.Sportsman is null in gradeServiceImpl.add ");
        }
        
        if (grade.getEvent().getEventId()== null) {
            throw new IllegalArgumentException("grade.Event is null in gradeServiceImpl.add ");
        }
        Grade toAdd = gradeDAO.findById(grade.getSportsman().getSportsmanId(), grade.getEvent().getEventId());
        if (!(toAdd== null)) {
            throw new IllegalArgumentException("already exist object in gradeServiceImpl.add ");
        }

        Grade gradeToAdd = mapper.map(grade, Grade.class);

        gradeDAO.persist(gradeToAdd);
        return mapper.map(gradeToAdd, GradeDTO.class);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void remove(GradeDTO grade) throws ServiceFailureException {
        if (grade == null) {
            throw new IllegalArgumentException("grade is null in  gradeServiceImpl.remove ");
        }

        if (grade.getSportsman().getSportsmanId()== null) {
            throw new IllegalArgumentException("grade.Sportsman is null in gradeServiceImpl.remove ");
        }
        
        if (grade.getEvent().getEventId()== null) {
            throw new IllegalArgumentException("grade.Event is null in gradeServiceImpl.remove ");
        }
        
        Grade toRemove = gradeDAO.findById(grade.getSportsman().getSportsmanId(), grade.getEvent().getEventId());
        if (toRemove== null) {
            throw new IllegalArgumentException("not exist object in gradeServiceImpl.remove ");
        }
             gradeDAO.remove(toRemove);
    }
    

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public GradeDTO edit(GradeDTO grade) throws ServiceFailureException {
        if (grade == null) {
            throw new IllegalArgumentException("grade is null in  gradeServiceImpl.remove ");
        }

        if (grade.getSportsman().getSportsmanId()== null) {
            throw new IllegalArgumentException("grade.Sportsman is null in gradeServiceImpl.remove ");
        }
        
        if (grade.getEvent().getEventId()== null) {
            throw new IllegalArgumentException("grade.Event is null in gradeServiceImpl.remove ");
        }
        
        Grade toEdit = gradeDAO.findById(grade.getSportsman().getSportsmanId(), grade.getEvent().getEventId());
        if (toEdit== null) {
            throw new IllegalArgumentException("not exist object in gradeServiceImpl.edit ");
        }
        Grade gradeToAdd = mapper.map(grade, Grade.class);
        
        gradeDAO.edit(gradeToAdd);
        return mapper.map(gradeToAdd, GradeDTO.class);
        
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Override
    @Transactional(readOnly = true)
    public GradeDTO findById(Long sportsmanid, Long eventid) throws ServiceFailureException {
       if (sportsmanid == null) {
            throw new IllegalArgumentException("grade.Sportsman is null in gradeServiceImpl.findById ");
        }
        
        if (eventid == null) {
            throw new IllegalArgumentException("grade.Event is null in gradeServiceImpl.findById ");
        }

        Grade grade = gradeDAO.findById(sportsmanid, eventid);
        GradeDTO gradeDTO = null;
        if (grade != null) {
            gradeDTO = mapper.map(grade, GradeDTO.class);
        }
        return gradeDTO;
    }
    
    
   
}