/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.service;


import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.SportDTO;


import java.util.List;

/**
 *
 * @author Maria
 */
public interface SportService {  

    public SportDTO add(SportDTO sport) throws ServiceFailureException;

    public void remove(SportDTO sport) throws ServiceFailureException;

    public SportDTO edit(SportDTO sport) throws ServiceFailureException;

    public SportDTO findById(Long id) throws ServiceFailureException;

    public List<SportDTO> getAll() throws ServiceFailureException;
    
    public List<SportDTO> findByName(String name) throws ServiceFailureException;
    
   public List<EventDTO> getEvents(SportDTO sport) throws ServiceFailureException;

    
}
