package de.othr.insurance.model;

import de.othr.insurance.converter.PolicyTypeConverter;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyApplicationDTO;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.BankService;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;
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
    @Inject
    PolicyTypeConverter polTypeConv;
    @Inject
    BankService bank;
    
    private List<Policy> policies;
    private int duration;
    private long itemId;
    private PolicyType policyType;
    private List<PolicyType> policyTypes;
    private PolicyType selectedPolicyType;
    private Policy policy;
    private Date startDate;
    private Policy pol;
    private HtmlDataTable dataTable;
    private HtmlInputHidden dataItemId = new HtmlInputHidden(); 

    public HtmlInputHidden getDataItemId() {
        return dataItemId;
    }

    public void setDataItemId(HtmlInputHidden dataItemId) {
        this.dataItemId = dataItemId;
    }

    public Policy getPol() {
        return pol;
    }

    public void setPol(Policy pol) {
        this.pol = pol;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

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
        if(bank.doTransfer() != null){
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
        } else {
            return null;
        }  
    }
    
    public String  cancelPol(Policy policy){
        polService.cancelPolicy(policy);
        return "policies";
    }
}
