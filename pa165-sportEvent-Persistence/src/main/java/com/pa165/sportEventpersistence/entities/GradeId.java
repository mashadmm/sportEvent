/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.entities;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
 
@Embeddable
public class GradeId implements java.io.Serializable {
 
	private Sportsman sportsman;
        private Event event;
 
	@ManyToOne(fetch = FetchType.LAZY) 
	public Sportsman getSportsman() {
		return sportsman;
	}
 
	public void setSportsman(Sportsman sportsman) {
		this.sportsman = sportsman;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	public Event getEvent() {
		return event;
	}
 
	public void setEvent(Event event) {
		this.event = event;
	}
 
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        GradeId that = (GradeId) o;
 
        if (sportsman != null ? !sportsman.equals(that.sportsman) : that.sportsman != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (sportsman != null ? sportsman.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
 
}