package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy="custID", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DamageCase> damagecases = new ArrayList<>();

    @OneToMany(mappedBy="custID",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Policy> policies = new ArrayList<>();

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