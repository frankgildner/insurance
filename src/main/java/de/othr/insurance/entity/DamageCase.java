package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class DamageCase extends BaseEntity implements Serializable{
    
    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private DamageType damageType;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    
    @Getter
    @Setter
    private double costs;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Policy policyNr;
    
    @Getter
    @Setter
    private double refund;

    public DamageCase() {
    }

    public DamageCase(String description, DamageType damageType, Policy policyNr, Customer custID, double costs, double refund) {
        this.description = description;
        this.damageType = damageType;
        this.custID = custID;
        this.costs = costs;
        this.policyNr = policyNr;
        this.refund = refund;
    }
}
