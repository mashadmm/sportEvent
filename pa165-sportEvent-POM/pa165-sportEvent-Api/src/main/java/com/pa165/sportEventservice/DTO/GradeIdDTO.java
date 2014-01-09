/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.DTO;


public class GradeIdDTO implements java.io.Serializable {
 
	private SportsmanDTO sportsman;
        private EventDTO event;
 
	
	public SportsmanDTO getSportsman() {
		return sportsman;
	}
 
	public void setSportsman(SportsmanDTO sportsman) {
		this.sportsman = sportsman;
	}
 
	
	public EventDTO getEvent() {
		return event;
	}
 
	public void setEvent(EventDTO event) {
		this.event = event;
	}
 
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        GradeIdDTO that = (GradeIdDTO) o;
 
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