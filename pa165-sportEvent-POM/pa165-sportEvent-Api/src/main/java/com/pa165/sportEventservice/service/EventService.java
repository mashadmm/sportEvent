/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.service;


import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import java.util.Date;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Maria
 */
public interface EventService {  

    public EventDTO add(EventDTO event) throws ServiceFailureException;

    public void remove(EventDTO event) throws ServiceFailureException;

    public EventDTO edit(EventDTO event) throws ServiceFailureException;

    public EventDTO findById(Long id) throws ServiceFailureException;

    public List<EventDTO> getAll() throws ServiceFailureException;
    
    public List<EventDTO> getAllUpToDate() throws ServiceFailureException;
    
    public List<EventDTO> findByName(String name) throws ServiceFailureException;
    
    public List<EventDTO> findByDates(Date startDate, Date endDate) throws ServiceFailureException;
    
    public List<SportsmanDTO> getSportsmans(EventDTO event) throws ServiceFailureException;

    public Map<SportsmanDTO, GradeDTO> getSportsmansWithGrades(EventDTO event) throws ServiceFailureException;
}
