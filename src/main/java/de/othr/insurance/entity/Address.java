package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address extends BaseEntity implements Serializable {
    private String street;
    private int postCode;
    private String city;
    private String country;
    
    public Address(){
    }

    public Address(String street, int postCode, String city, String country) {
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }
    
    
}
