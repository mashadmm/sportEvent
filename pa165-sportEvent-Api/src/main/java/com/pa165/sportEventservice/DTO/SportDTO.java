/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.DTO;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Maria
 * 
  */


public class SportDTO implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long sportId;
    
    private String name;
    private String description;
    
    private List<EventDTO> events = new ArrayList<EventDTO>(0);
    
    
    public Long getSportId() {
		return this.sportId;
	}
 
    public void setSportId(Long sportId) {
		this.sportId = sportId;
	}
    
    
    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
         
    
    public String getDescription() {
		return this.description;
	}
 
    public void setDescription(String description) {
		this.description = description;
	}
     
    
     public List<EventDTO> getResults() {
		return this.events;
	}
 
     public void setResults(List<EventDTO> events) {
		this.events = events;
	}

    @Override
    public int hashCode() {
        int hash = 537;
        hash += (sportId != null ? sportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SportDTO)) {
            return false;
        }
        SportDTO other = (SportDTO) object;
        if ((this.sportId == null && other.sportId != null) || (this.sportId != null && !this.sportId.equals(other.sportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.entity.Sport[ id=" + sportId + " ]";
    }
    
}