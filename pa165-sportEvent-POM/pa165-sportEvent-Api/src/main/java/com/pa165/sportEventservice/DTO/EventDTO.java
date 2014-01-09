/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.DTO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Maria
 * 
  */


public class EventDTO implements java.io.Serializable {
    
   
    private Long eventId;
    
    private String name;
    private Date dateOfEvent;
    private SportDTO sport;
    
    private List<GradeDTO> results = new ArrayList<GradeDTO>(0);
    
    
 
    public Long getEventId() {
		return this.eventId;
	}
 
    public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
    
   
    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
    
    public Date getDateOfEvent() {
		return this.dateOfEvent;
	}
 
     public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
     
    public List<GradeDTO> getResults() {
		return this.results;
	}
 
     public void setResults(List<GradeDTO> results) {
		this.results = results;
	}

    public SportDTO getSport() {
		return this.sport;
	}
 
     public void setSport(SportDTO sport) {
		this.sport = sport;
	}
 
     
    @Override
    public int hashCode() {
        int hash = 137;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventDTO)) {
            return false;
        }
        EventDTO other = (EventDTO) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.entity.Event[ id=" + eventId + " ]";
    }
    
}
