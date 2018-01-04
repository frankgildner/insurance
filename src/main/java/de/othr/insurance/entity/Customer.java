package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;

@Entity
public class Customer extends BaseEntity implements Serializable{
    private String prename;
    private String surname;
    @Embedded
    private Address address;
    private String iban;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    private String password;
    private String email;

    public Customer(){
    }
    
    public Customer(String email, String prename, String surname, Address address, String iban, Date birthday, String password) {
        this.prename = prename;
        this.surname = surname;
        this.address=address;
        this.iban = iban;
        this.birthday=birthday;
        this.password = password;
        this.email = email;   
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
}