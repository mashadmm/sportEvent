/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

 
@Entity
@NamedQueries({
        @NamedQuery(name = "grade.findBySportsman", query = "SELECT u FROM Grade u WHERE u.pk.sportsman.sportsmanId = :id"),
        @NamedQuery(name = "grade.findByID", query = "SELECT u FROM Grade u WHERE u.pk.sportsman.sportsmanId = :id1 AND u.pk.event.eventId = :id2"),
        @NamedQuery(name = "grade.findByEvent", query = "SELECT u FROM Grade u WHERE u.pk.event.eventId = :id ORDER BY u.grade DESC"),

         
})
@Table(name = "grade")
@AssociationOverrides({
		@AssociationOverride(name = "pk.sportsman", 
			joinColumns = @JoinColumn(name = "SPORTSMANID")),
		@AssociationOverride(name = "pk.event", 
			joinColumns = @JoinColumn(name = "EVENTID")) })
public class Grade implements java.io.Serializable {
 
	private GradeId pk = new GradeId();
	private Integer grade;
	
 
	public Grade() {
	}
 
	@EmbeddedId
	public GradeId getPk() {
		return pk;
	}
 
	public void setPk(GradeId pk) {
		this.pk = pk;
	}
 
	@Transient
	public Sportsman getSportsman() {
		return getPk().getSportsman();
	}
 
	public void setSportsman(Sportsman sportsman) {
		getPk().setSportsman(sportsman);
	}
 
	@Transient
	public Event getEvent() {
		return getPk().getEvent();
	}
 
	public void setEvent(Event event) {
		getPk().setEvent(event);
	}
 
	 
	@Column(name = "GRADE", length = 10)
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
 
		Grade that = (Grade) o;
 
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
 
		return true;
	}
 
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
