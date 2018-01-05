package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="PolicyType")
public class PolicyType extends BaseEntity implements Serializable {
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String name;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private double selfparticipation;
    
    @Column(nullable = false)
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
