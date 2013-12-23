/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.service;


import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;

import java.util.List;

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
    
   
}
