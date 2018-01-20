package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="Policy")
public class Policy extends BaseEntity implements Serializable {
    
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
    private double price;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private long itemID;
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private PolicyType policyTypeID;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String status;
    
    public Policy() {
    }

    public Policy(Date startDate, int duration, long itemID, double price, Customer custID, PolicyType policyTypeID) {
        this.startDate = startDate;
        this.duration = duration;
        this.price = price;
        this.custID = custID;
        this.itemID = itemID;
        this.policyTypeID = policyTypeID;
        this.status = "running";
    }
    
    public Long getPolicyNumber(){
        return this.getId();
    }
}

