package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="PolicyApplicationDTO")
public class PolicyApplicationDTO extends BaseEntity implements Serializable{
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String prename;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String surname;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String street;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String city;
    
    @Column(nullable = false)
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthdate;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private long itemID;
    
    @Column(nullable = false)
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private int duration;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String password;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String email;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String iban;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private int postalCode;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String country;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private PolicyType policyType;
    
    public PolicyApplicationDTO() {
    }

    public PolicyApplicationDTO(String prename, String surname, String iban, String street, int postalCode, String city, String country, Date birthdate, long itemID, Date startDate, int duration, String password, String email, PolicyType policyType) {
        this.prename = prename;
        this.surname = surname;
        this.street = street;
        this.iban = iban;
        this.city = city;
        this.postalCode = postalCode;
        this.birthdate = birthdate;
        this.itemID = itemID;
        this.startDate = startDate;
        this.duration = duration;
        this.password = password;
        this.email = email;
        this.policyType = policyType;
        this.country = country;
    }
}
