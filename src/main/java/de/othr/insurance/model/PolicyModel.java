package de.othr.insurance.model;

import de.othr.insurance.converter.PolicyTypeConverter;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyApplicationDTO;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import java.util.Date;
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
    CustModel custModel;
    @Inject 
    PolicyTypeService poltypeService;
    @Inject
    PolicyTypeConverter polTypeConv;
    
    private List<Policy> policies;
    private int duration;
    private long itemId;
    private PolicyType policyType;
    private List<PolicyType> policyTypes;
    private PolicyType selectedPolicyType;
    private Policy policy;
    private Date startDate;

    public PolicyTypeConverter getPolTypeConv() {
        return polTypeConv;
    }

    public void setPolTypeConv(PolicyTypeConverter polTypeConv) {
        this.polTypeConv = polTypeConv;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
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
        this.policy = polService.newPolicy(new PolicyApplicationDTO(custModel.getFirstName(),
                custModel.getLastname(),
                custModel.getIban(),
                custModel.getStreet(),
                custModel.getPostCode(),
                custModel.getCity(),
                custModel.getCountry(),
                custModel.getBirthday(),
                this.getItemId(),
                this.getStartDate(),
                this.getDuration(),
                custModel.getPassword(),
                custModel.getEmail(),
                this.getSelectedPolicyType()));
        this.setItemId(0);
        this.setDuration(0);
        return policy;
    }
}
