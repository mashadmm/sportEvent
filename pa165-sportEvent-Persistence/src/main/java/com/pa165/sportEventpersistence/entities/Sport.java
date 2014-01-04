/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GenerationType;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Maria
 * 
 * Entity "Sport" contains list of sports for events,
 * entity fields - id, name, description
 * relationships to another entities: many-to-one with Event 
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "sportsman.findByName", query = "SELECT u FROM Sport AS u WHERE u.name LIKE :name")
         
})
@Table(name = "sport")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sport implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long sportId;
    
    private String name;
    private String description;
    
    private List<Event> events = new ArrayList<Event>(0);
    
    public Sport() {
	}
 
    public Sport(String name, String description) {
		this.name = name;
                this.description = description;
		
	}
 
    public Sport(String name, String description,
			List<Event> events) {
		this.name = name;
                this.description = description;
		this.events = events;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPORTID")
    public Long getSportId() {
		return this.sportId;
	}
 
    public void setSportId(Long sportId) {
		this.sportId = sportId;
	}
    
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
         
    @Column(name = "DESCRIPTION", length = 50)
    public String getDescription() {
		return this.description;
	}
 
    public void setDescription(String description) {
		this.description = description;
	}
     
     @XmlAttribute
     @XmlTransient
     @OneToMany(mappedBy = "sport")
     public List<Event> getEvents() {
		return this.events;
	}
 
     public void setEvents(List<Event> events) {
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
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
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