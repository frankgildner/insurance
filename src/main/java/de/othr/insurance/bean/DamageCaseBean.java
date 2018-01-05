package de.othr.insurance.bean;

import de.othr.insurance.converter.DamageTypeConverter;
import de.othr.insurance.converter.PolicyConverter;
import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.DamageCase;
import de.othr.insurance.entity.DamageType;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.service.DamageCaseService;
import de.othr.insurance.service.DamageTypeService;
import de.othr.insurance.service.PolicyService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class DamageCaseBean implements Serializable {
    
    @Inject 
    CustomerBean custModel;
    
    @Inject   
    DamageTypeService damTypeServ;
    
    @Inject
    @Getter
    @Setter
    DamageTypeConverter damTypeConv;
    
    @Inject
    DamageCaseService damCaseServ;
    
    @Inject
    @Getter
    @Setter        
    PolicyConverter policyConv;
    
    @Inject
    PolicyService polServ;
    
    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    private DamageType damType;
    
    @Getter
    @Setter
    private List<DamageType> damageTypes;
    
    @Getter
    @Setter
    private DamageType selectedDamageType;
    
    @Getter
    @Setter
    private Customer custID;
    
    @Getter
    @Setter
    private double costs;
    
    @Getter
    @Setter
    private List<DamageCase> damageCases;
    
    @Getter
    @Setter
    private DamageCase damageCase;
    
    @Getter
    @Setter
    private Policy selectedPolicy;
    
    @Getter
    @Setter
    private List<Policy> policies;

    public List<Policy> getPoliciesByCustomer(){
        List<Policy> allCustPolicies = polServ.getPoliciesByCustomer(custModel.getCustomer());
        List<Policy> allCustPoliciesRunning = new ArrayList<>();
        for(Policy policy: allCustPolicies){
            if (policy.getStatus().equals("running")){
                allCustPoliciesRunning.add(policy);
            }
        }
        return allCustPoliciesRunning;
    }

    public List<DamageType> getDamageTypes(){
        List<DamageType> allDT = damTypeServ.getDamageTypes();
        return allDT;
    }
    
    public void init(){
        this.damageTypes = this.getDamageTypes();
        this.policies = this.getPoliciesByCustomer();
    }
    
    public List<DamageCase> getDamageCaseByCustomer(){
        this.damageCases = damCaseServ.getDamageCaseByCustomer(custModel.getCustomer());
        return damageCases;
    }
    
    public DamageCase createDamageCase(){
        if(this.costs > 0){
            this.damageCase = damCaseServ.newDamagecase(
                this.description, 
                this.selectedPolicy, 
                this.selectedDamageType, 
                custModel.getCustomer(), 
                this.costs);
            this.description = "";
            this.selectedDamageType = null;
            this.selectedPolicy = null;
            this.costs = 0;
            return damageCase;
        } else {
            FacesContext.getCurrentInstance().addMessage("dcForm:createDC",new FacesMessage("Costs cannot be 0 or smaller!"));   
            return null;
        }
        
    }
    
}
