package de.othr.insurance.bean;

import de.othr.insurance.entity.DamageType;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.DamageTypeService;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class AdministrationBean implements Serializable {
    
    @Inject 
    PolicyTypeService poltypeService;
    
    @Inject
    DamageTypeService damTypeService;
    
    @Getter
    @Setter
    private String policyTypeName;
    
    @Getter
    @Setter
    private double selfpart;
    
    @Getter
    @Setter
    private int costsPerDay;
    
    @Getter
    @Setter
    private PolicyType polType;
    
    @Getter
    @Setter
    private String damageTypeName;
    
    @Getter
    @Setter
    private DamageType damType;

    
    public PolicyType createPolicyType(){
        if(!this.policyTypeName.equals("") &&
                this.selfpart > -1 &&
                this.costsPerDay > 0){
            this.polType = poltypeService.createPolicyType(policyTypeName, selfpart, costsPerDay);
        }
        this.policyTypeName = "";
        this.selfpart = 0;
        this.costsPerDay = 0;
        return this.polType;
    }
    
    public DamageType createDamageType(){
        if(!this.damageTypeName.equals("")){
            this.damType = damTypeService.createDamageType(this.damageTypeName);
        }
        this.damageTypeName = "";
        return this.damType;
    }
}
