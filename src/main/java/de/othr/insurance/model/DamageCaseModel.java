package de.othr.insurance.model;

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
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class DamageCaseModel implements Serializable {
    
    @Inject 
    CustomerModel custModel;
    @Inject 
    DamageTypeService damTypeServ;
    @Inject
    DamageTypeConverter damTypeConv;
    @Inject
    DamageCaseService damCaseServ;
    @Inject
    PolicyConverter policyConv;
    @Inject
    PolicyService polServ;
    
    private String description;
    private DamageType damType;
    private List<DamageType> damageTypes;
    private DamageType selectedDamageType;
    private Customer custID;
    private double costs;
    private List<DamageCase> damageCases;
    private DamageCase damageCase;
    private Policy selectedPolicy;
    private List<Policy> policies;

    public PolicyConverter getPolicyConv() {
        return policyConv;
    }

    public void setPolicyConv(PolicyConverter policyConv) {
        this.policyConv = policyConv;
    }

    public Policy getSelectedPolicy() {
        return selectedPolicy;
    }

    public void setSelectedPolicy(Policy selectedPolicy) {
        this.selectedPolicy = selectedPolicy;
    }

    public List<Policy> getPolicies() {
       return policies;
    }
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

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public List<DamageCase> getDamageCases() {
        return damageCases;
    }

    public void setDamageCases(List<DamageCase> damageCases) {
        this.damageCases = damageCases;
    }

    public DamageCase getDamageCase() {
        return damageCase;
    }

    public void setDamageCase(DamageCase damageCase) {
        this.damageCase = damageCase;
    }

    public DamageType getSelectedDamageType() {
        return selectedDamageType;
    }

    public void setSelectedDamageType(DamageType selectedDamageType) {
        this.selectedDamageType = selectedDamageType;
    }
    
    public DamageTypeConverter getDamTypeConv() {
        return damTypeConv;
    }

    public void setDamTypeConv(DamageTypeConverter damTypeConv) {
        this.damTypeConv = damTypeConv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DamageType getDamType() {
        return damType;
    }

    public void setDamType(DamageType damType) {
        this.damType = damType;
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
        this.damageCase = damCaseServ.newDamagecase(
                this.description, 
                this.selectedPolicy, 
                this.selectedDamageType, 
                custModel.getCustomer(), 
                //TODO
                this.costs);
        this.description = "";
        this.selectedDamageType = null;
        this.selectedPolicy = null;
        this.costs = 0;
        return damageCase;
    }
    
}
