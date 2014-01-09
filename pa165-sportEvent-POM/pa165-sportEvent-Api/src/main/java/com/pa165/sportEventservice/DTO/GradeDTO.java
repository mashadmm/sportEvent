/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventservice.DTO;


 

public class GradeDTO implements java.io.Serializable {
 
	private GradeIdDTO pk = new GradeIdDTO();
	private Integer grade;
	
 
	public GradeDTO() {
	}
 
	
	public GradeIdDTO getPk() {
		return pk;
	}
 
	public void setPk(GradeIdDTO pk) {
		this.pk = pk;
	}
 
	
	public SportsmanDTO getSportsman() {
		return getPk().getSportsman();
	}
 
	public void setSportsman(SportsmanDTO sportsman) {
		getPk().setSportsman(sportsman);
	}
 
	
	public EventDTO getEvent() {
		return getPk().getEvent();
	}
 
	public void setEvent(EventDTO event) {
		getPk().setEvent(event);
	}
 
	 
	
	public Integer getGrade() {
		return this.grade;
	}
 
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
 
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		GradeDTO that = (GradeDTO) o;
 
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
 
		return true;
	}
 
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
