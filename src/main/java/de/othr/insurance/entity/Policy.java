package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Policy extends BaseEntity implements Serializable {
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Getter
    @Setter
    private int duration;
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    @Getter
    @Setter
    private long itemID;
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private PolicyType policyTypeID;
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
}

