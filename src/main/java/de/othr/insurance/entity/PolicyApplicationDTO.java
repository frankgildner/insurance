package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PolicyApplicationDTO extends BaseEntity implements Serializable{
    
    @Getter
    @Setter
    private String prename;
    
    @Getter
    @Setter
    private String surname;
    
    @Getter
    @Setter
    private String street;
    
    @Getter
    @Setter
    private String city;
    
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthdate;
    
    @Getter
    @Setter
    private long itemID;
    
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Getter
    @Setter
    private int duration;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String iban;
    
    @Getter
    @Setter
    private int postalCode;
    
    @Getter
    @Setter
    private String country;
    
    @Getter
    @Setter
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
