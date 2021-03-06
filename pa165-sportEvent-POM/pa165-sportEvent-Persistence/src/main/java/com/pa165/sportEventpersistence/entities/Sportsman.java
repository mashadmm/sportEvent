/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.entities;

import java.util.ArrayList;
import javax.persistence.GenerationType;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maria
 * 
 * Entity "Sportsman" contains list of sportsmans, who could register to sport events,
 * entity fields - id, name, lastname, date of birth
 * 
 * relationships to another entities: many-to-many with Event
 * associative entity with extra column "grade": Result
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "sportsman.findByLastname", query = "SELECT u FROM Sportsman AS u WHERE u.lastname LIKE :lastname AND (u.userRole is null or u.userRole != 'ROLE_ADMIN') ")
         
})
@Table(name = "SPORTSMAN")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sportsman implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long sportsmanId;
    
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String userName;
    private String pwd;
    private String userRole;
    
    private List<Grade> results = new ArrayList<Grade>(0);
    
    public Sportsman() {
	}
 
    public Sportsman(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
 
    public Sportsman(String name, String lastname,
			List<Grade> results) {
		this.name = name;
		this.lastname = lastname;
		this.results = results;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPORTSMANID")
    public Long getSportsmanId() {
		return this.sportsmanId;
	}
 
    public void setSportsmanId(Long sportsmanId) {
		this.sportsmanId = sportsmanId;
	}
    
    @Column(name = "NAME", nullable = false, length = 10)
    public String getName() {
		return this.name;
	}
 
    public void setName(String name) {
		this.name = name;
	}
    
     @Column(name = "LASTNAME", nullable = false, length = 10)
     public String getLastname() {
		return this.lastname;
	}
 
     public void setLastname(String lastname) {
		this.lastname = lastname;
	}
     
     @Temporal(TemporalType.DATE)
     @Column(name = "DATEOFBIRTH", nullable = false, length = 10)
     public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
 
     public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
     
     
     
     @XmlAttribute
     @XmlTransient
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.sportsman", cascade=CascadeType.ALL)
     public List<Grade> getResults() {
		return this.results;
	}
 
     public void setResults(List<Grade> results) {
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
        if (!(object instanceof Sportsman)) {
            return false;
        }
        Sportsman other = (Sportsman) object;
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


 


 	
 
	
 
	
 
	
 
	
 
	
 
