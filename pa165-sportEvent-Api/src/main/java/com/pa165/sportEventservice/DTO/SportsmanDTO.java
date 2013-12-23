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


public class SportsmanDTO implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long sportsmanId;
    
    private String name;
    private String lastname;
    private Date dateOfBirth;
    
    private List<GradeDTO> results = new ArrayList<GradeDTO>(0);
    
    public SportsmanDTO() {
	}
 
    public SportsmanDTO(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
 
    public SportsmanDTO(String name, String lastname,
			List<GradeDTO> results) {
		this.name = name;
		this.lastname = lastname;
		this.results = results;
	}

    public Long getSportsmanId() {
		return this.sportsmanId;
	}
 
    public void setSportsmanId(Long sportsmanId) {
		this.sportsmanId = sportsmanId;
	}
    

    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
  
     public String getLastname() {
		return this.lastname;
	}
 
     public void setLastname(String lastname) {
		this.lastname = lastname;
	}
     

     public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
 
     public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
     
 
     public List<GradeDTO> getResults() {
		return this.results;
	}
 
     public void setResults(List<GradeDTO> results) {
		this.results = results;
	}

    @Override
    public int hashCode() {
        int hash = 177;
        hash += (sportsmanId != null ? sportsmanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SportsmanDTO)) {
            return false;
        }
        SportsmanDTO other = (SportsmanDTO) object;
        if ((this.sportsmanId == null && other.sportsmanId != null) || (this.sportsmanId != null && !this.sportsmanId.equals(other.sportsmanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.entity.Sportsman[ id=" + sportsmanId + " ]";
    }
    
}


 


 	
 
	
 
	
 
	
 
	
 
	
 
