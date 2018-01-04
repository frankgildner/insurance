package de.othr.insurance.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Policy extends BaseEntity implements Serializable {
    private Date startDate;
    private int duration;
    private double price;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    private long itemID;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private PolicyType policyTypeID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustID() {
        return custID;
    }

    public void setCustID(Customer custID) {
        this.custID = custID;
    }

    public PolicyType getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(PolicyType policyTypeID) {
        this.policyTypeID = policyTypeID;
    }
}

