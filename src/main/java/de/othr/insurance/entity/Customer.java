package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Customer extends BaseEntity implements Serializable{
    @Getter
    @Setter
    private String prename;
    @Getter
    @Setter
    private String surname;
    @Getter
    @Setter
    @Embedded
    private Address address;
    @Getter
    @Setter
    private String iban;
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
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
}