/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.service;


import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;

import java.util.List;

/**
 *
 * @author Maria
 */
public interface GradeService {  

//    public GradeDTO add(GradeDTO grade) throws ServiceFailureException;
//
//    public void remove(GradeDTO grade) throws ServiceFailureException;
//
//    public GradeDTO edit(GradeDTO grade) throws ServiceFailureException;
//
//    public GradeDTO findById(Long sportsmanid, Long eventid) throws ServiceFailureException;
//
//    public List<GradeDTO> getAll() throws ServiceFailureException;
//    
//    public List<GradeDTO> findByEvent(EventDTO event) throws ServiceFailureException;
//    
//    public List<GradeDTO> findBySportsman(SportsmanDTO sportsman) throws ServiceFailureException;
      
   
    public int getPlace(EventDTO event, SportsmanDTO sportsman) throws ServiceFailureException;
    
    public void putGrade(SportsmanDTO sportsman, EventDTO event, int grade) throws ServiceFailureException;
    
    public void putGrade(Long sportsmanId, Long eventId, int eventgrade) throws ServiceFailureException;
    
    
}
