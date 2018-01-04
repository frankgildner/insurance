package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PolicyType extends BaseEntity implements Serializable {
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private double selfparticipation;
    
    @Getter
    @Setter
    private double pricePerDay;

    public PolicyType() {
    }

    public PolicyType(String name, double selfparticipation, double pricePerDay) {
        this.name = name;
        this.selfparticipation = selfparticipation;
        this.pricePerDay = pricePerDay;
    }
    
    @Override
    public String toString() {
        return name + "(" + selfparticipation*100 + " % SP, " + pricePerDay + " €/day)";
    }
    
    
}
