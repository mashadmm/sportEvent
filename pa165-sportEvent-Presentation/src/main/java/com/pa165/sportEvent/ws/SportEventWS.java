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
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

/**
 *
 * @author Maria
 */

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface SportEventWS{
    
 
	
          // Sportsman Manager
    
         List<SportsmanDTO> sportsmanManagerfindAll();
        
         void sportsmanManagersave(SportsmanDTO sportsman);
         
         void sportsmanManagerupdate(SportsmanDTO sportsman);
         
         void sportsmanManagerdelete(SportsmanDTO sportsman);
         
         SportsmanDTO sportsmanManagerfindById(Long sportsmanid);
         
         // Event
         
         List<EventDTO> eventManagerfindAll();
        
         void eventManagersave(EventDTO event);
         
         void eventManagerupdate(EventDTO event);
         
         void eventManagerdelete(EventDTO event);
         
         EventDTO eventManagerfindById(Long eventid) throws ServiceFailureException;
         
         // Grade
         
         public void gradeManagerGetPlace(EventDTO eventDTO, SportsmanDTO sportsmanDTO);
         public void gradeManagerPutGrade(EventDTO eventDTO, SportsmanDTO sportsmanDTO, int grade);
            
        
}