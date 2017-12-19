package de.othr.insurance.model;

import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PolicyTypeModel implements Serializable {
    
    @Inject 
    PolicyTypeService poltypeService;
    
    private String name;
    private double selfpart;
    private int costsPerDay;
    private PolicyType polType;

    public PolicyTypeService getPoltypeService() {
        return poltypeService;
    }

    public void setPoltypeService(PolicyTypeService poltypeService) {
        this.poltypeService = poltypeService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSelfpart() {
        return selfpart;
    }

    public void setSelfpart(double selfpart) {
        this.selfpart = selfpart;
    }

    public int getCostsPerDay() {
        return costsPerDay;
    }

    public void setCostsPerDay(int costsPerDay) {
        this.costsPerDay = costsPerDay;
    }

    public PolicyType getPolType() {
        return polType;
    }

    public void setPolType(PolicyType polType) {
        this.polType = polType;
    }
    
    public PolicyType createPolicyType(){
        if(!this.name.equals("") &&
                this.selfpart > 0 &&
                this.costsPerDay > 0){
            this.polType = poltypeService.createPolicyType(name, selfpart, costsPerDay);
        }
        
        return this.polType;
    }
}
