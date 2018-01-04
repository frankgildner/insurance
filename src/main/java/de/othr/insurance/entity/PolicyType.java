package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class PolicyType extends BaseEntity implements Serializable {
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
    public String toString() {
        return name + "(" + selfparticipation*100 + " % SP, " + pricePerDay + " €/day)";
    }
    
    
}
