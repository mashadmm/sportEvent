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
import java.util.Map;

/**
 *
 * @author Maria
 */
public interface SportsmanService {  

    public SportsmanDTO add(SportsmanDTO sportsman) throws ServiceFailureException;

    public void remove(SportsmanDTO sportsman) throws ServiceFailureException;

    public SportsmanDTO edit(SportsmanDTO sportsman) throws ServiceFailureException;

    public SportsmanDTO findById(Long id) throws ServiceFailureException;

    public List<SportsmanDTO> getAll() throws ServiceFailureException;
    
    
    public List<SportsmanDTO> findByLastname(String lastname) throws ServiceFailureException;
    
    public SportsmanDTO registerToEvent(SportsmanDTO sportsman, EventDTO event) throws ServiceFailureException;
    
    public Map<EventDTO, GradeDTO> getEventsWithGrades(SportsmanDTO sportsman) throws ServiceFailureException ;
    
    public SportsmanDTO getByLogin(String login) throws ServiceFailureException;
   
}
