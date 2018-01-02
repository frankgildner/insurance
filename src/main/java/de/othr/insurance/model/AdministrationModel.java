package de.othr.insurance.model;

import de.othr.insurance.entity.DamageType;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.DamageTypeService;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AdministrationModel implements Serializable {
    
    @Inject 
    PolicyTypeService poltypeService;
    @Inject
    DamageTypeService damTypeService;
    
    private String policyTypeName;
    private double selfpart;
    private int costsPerDay;
    private PolicyType polType;
    private String damageTypeName;
    private DamageType damType;

    public DamageType getDamType() {
        return damType;
    }

    public void setDamType(DamageType damType) {
        this.damType = damType;
    }

    public String getPolicyTypeName() {
        return policyTypeName;
    }

    public void setPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }

    public String getDamageTypeName() {
        return damageTypeName;
    }

    public void setDamageTypeName(String damageTypeName) {
        this.damageTypeName = damageTypeName;
    }
    
    public PolicyTypeService getPoltypeService() {
        return poltypeService;
    }

    public void setPoltypeService(PolicyTypeService poltypeService) {
        this.poltypeService = poltypeService;
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
        if(!this.policyTypeName.equals("") &&
                this.selfpart > 0 &&
                this.costsPerDay > 0){
            this.polType = poltypeService.createPolicyType(policyTypeName, selfpart, costsPerDay);
        }
        
        return this.polType;
    }
    
    public DamageType createDamageType(){
        if(!this.damageTypeName.equals("")){
            this.damType = damTypeService.createDamageType(this.damageTypeName);
        }
        return this.damType;
    }
}
