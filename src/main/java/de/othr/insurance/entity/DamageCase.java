package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class DamageCase extends BaseEntity implements Serializable{
    private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private DamageType damageType;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    private double costs;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Policy policyNr;
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

    public Policy getPolicyNr() {
        return policyNr;
    }

    public void setPolicyNr(Policy policyNr) {
        this.policyNr = policyNr;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Customer getCustID() {
        return custID;
    }

    public void setCustID(Customer custID) {
        this.custID = custID;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }
}
