package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="DamageCase")
public class DamageCase extends BaseEntity implements Serializable{
    
    @Column(nullable = false)
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
    
    @Column(nullable = false)
    @Getter
    @Setter
    private double costs;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Policy policyNr;
    
    @Column(nullable = false)
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
