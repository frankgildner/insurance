package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class PolicyType implements Serializable {
    @Id private long policyTypeID;
    private String name;
    private double selfparticipation;
    private double pricePerDay;

    public PolicyType() {
    }

    public PolicyType(String name, double selfparticipation, double pricePerDay) {
        this.name = name;
        this.selfparticipation = selfparticipation;
        this.pricePerDay = pricePerDay;
    }

    public long getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(long policyTypeID) {
        this.policyTypeID = policyTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSelfparticipation() {
        return selfparticipation;
    }

    public void setSelfparticipation(double selfparticipation) {
        this.selfparticipation = selfparticipation;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.policyTypeID ^ (this.policyTypeID >>> 32));
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
        final PolicyType other = (PolicyType) obj;
        if (this.policyTypeID != other.policyTypeID) {
            return false;
        }
        return true;
    }
    
    
}
