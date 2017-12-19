package de.othr.insurance.model;

import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyApplicationDTO;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PolicyModel implements Serializable{
    @Inject
    PolicyService polService;
    @Inject
    CustomerModel custModel;
    @Inject 
    PolicyTypeService poltypeService;
    
    private List<Policy> policies;
    private int duration;
    private long itemId;
    private PolicyType policyType;
    private List<PolicyType> policyTypes;
    private PolicyType selectedPolicyType;
    private Policy policy;

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public PolicyTypeService getPoltypeService() {
        return poltypeService;
    }

    public void setPoltypeService(PolicyTypeService poltypeService) {
        this.poltypeService = poltypeService;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    public PolicyType getSelectedPolicyType() {
        return selectedPolicyType;
    }

    public void setSelectedPolicyType(PolicyType selectedPolicyType) {
        this.selectedPolicyType = selectedPolicyType;
    }
    
    public void init(){
        this.policyTypes = this.getPolicyTypes();
    }
    
    public List<Policy> getPoliciesByCustomer(){
        this.policies = polService.getPoliciesByCustomer(custModel.getCustomer());
        return policies;
    }
    
    public List<PolicyType> getPolicyTypes(){
        List<PolicyType> x = poltypeService.getPolicyTypes();
        return x;
    }
    
    public Policy createPolicy(){
        this.policy = polService.newPolicy(new PolicyApplicationDTO());
        return null;
    }
}
