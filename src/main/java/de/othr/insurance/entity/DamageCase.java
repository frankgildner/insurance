package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DamageCase implements Serializable{
    @Id @GeneratedValue private long damageCaseId;
    private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private DamageType damageType;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private Customer custID;
    private double costs;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private long policyNr;

    public DamageCase() {
    }

    public DamageCase(String description, DamageType damageType, long policyNr, Customer custID, double costs) {
        this.description = description;
        this.damageType = damageType;
        this.custID = custID;
        this.costs = costs;
        this.policyNr = policyNr;
    }
    
    public long getDamageCaseId() {
        return damageCaseId;
    }

    public void setDamageCaseId(long damageCaseId) {
        this.damageCaseId = damageCaseId;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.damageCaseId ^ (this.damageCaseId >>> 32));
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
        final DamageCase other = (DamageCase) obj;
        if (this.damageCaseId != other.damageCaseId) {
            return false;
        }
        return true;
    }
    
}
