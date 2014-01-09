/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.entities;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.GenerationType;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author Maria
 * 
 * Entity "Event" contains list of events, that sportsman could register to,
 * entity fields - id, name, date of event, type of sport
 * 
 * relationships to another entities: many-to-many with Sportsman
 * associative entity with extra column "grade": Result
 * 
 * relationships to another entities: many-to-one with Sport
 *
 */

@Entity
@NamedQueries({
        
        @NamedQuery(name = "event.findByName", query = "SELECT u FROM Event AS u WHERE u.name LIKE :name"),
        @NamedQuery(name = "event.findByDates", query = "SELECT u FROM Event AS u WHERE u.dateOfEvent BETWEEN :startDate AND :endDate ")
         
})
@Table(name = "event")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long eventId;
    
    private String name;
    private Date dateOfEvent;
    private Sport sport;
    
    private List<Grade> results = new ArrayList<Grade>(0);
    
    public Event() {
	}
 
    public Event(String name, Date dateOfEvent) {
		this.name = name;
                this.dateOfEvent = dateOfEvent;
		
	}
 
    public Event(String name, Date dateOfEvent,
			List<Grade> results) {
		this.name = name;
		this.dateOfEvent = dateOfEvent;
		this.results = results;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENTID")
    public Long getEventId() {
		return this.eventId;
	}
 
    public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
    
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATEOFEVENT", nullable = false, length = 10)
     public Date getDateOfEvent() {
		return this.dateOfEvent;
	}
 
     public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
     
     @XmlAttribute
     @XmlTransient
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade=CascadeType.ALL)
     public List<Grade> getResults() {
		return this.results;
	}
 
     public void setResults(List<Grade> results) {
		this.results = results;
	}
     
     @XmlAttribute
     @XmlTransient
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "SPORTID", nullable = false)
     public Sport getSport() {
		return this.sport;
	}
 
     public void setSport(Sport sport) {
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
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
