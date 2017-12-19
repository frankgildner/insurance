package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PolicyApplicationDTO implements Serializable{

    @Id @GeneratedValue private long paDtoId;
    private String prename;
    private String surname;
    private String street;
    private String city;
    private Date birthdate;
    private long itemID;
    private String description;
    private Date startDate;
    private int duration;
    private String password;
    private String email;
    private String iban;
    private int postalCode;
    private String country;
    private PolicyType policyType;
    
    public PolicyApplicationDTO() {
    }

    public PolicyApplicationDTO(String prename, String surname, String iban, String street, int postalCode, String city, String country, Date birthdate, long itemID, String description, Date startDate, int duration, String password, String email, PolicyType policyType) {
        this.prename = prename;
        this.surname = surname;
        this.street = street;
        this.iban = iban;
        this.city = city;
        this.postalCode = postalCode;
        this.birthdate = birthdate;
        this.itemID = itemID;
        this.description = description;
        this.startDate = startDate;
        this.duration = duration;
        this.password = password;
        this.email = email;
        this.policyType = policyType;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getPaDtoId() {
        return paDtoId;
    }

    public void setPaDtoId(long paDtoId) {
        this.paDtoId = paDtoId;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.paDtoId ^ (this.paDtoId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PolicyApplicationDTO other = (PolicyApplicationDTO) obj;
        if (this.paDtoId != other.paDtoId) {
            return false;
        }
        return true;
    }
    
}
