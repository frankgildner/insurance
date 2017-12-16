package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address implements Serializable {
    @Id private long addressID;
    private String street;
    private int postCode;
    private String city;
    private String country;
    
    public Address(){
    }

    public Address(long addressID, String street, int postCode, String city, String country) {
        this.addressID = addressID;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }
    
    
}
