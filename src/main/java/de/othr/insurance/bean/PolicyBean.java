package de.othr.insurance.bean;

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
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class PolicyBean implements Serializable{
    
    @Inject
    PolicyService polService;
    
    @Inject
    CustomerBean custModel;
    
    @Inject 
    PolicyTypeService poltypeService;
    
    @Inject
    @Getter
    @Setter
    PolicyTypeConverter polTypeConv;
    
    @Inject
    BankService bank;
    
    @Getter
    @Setter
    private List<Policy> policies;
    
    @Getter
    @Setter
    private int duration;
    
    @Getter
    @Setter
    private long itemId;
    
    @Getter
    @Setter
    private PolicyType policyType;
    
    @Setter
    private List<PolicyType> policyTypes;
    
    @Getter
    @Setter
    private PolicyType selectedPolicyType;
    
    @Getter
    @Setter
    private Policy policy;
    
    @Getter
    @Setter
    private Date startDate;
    
    @Getter
    @Setter
    private Policy pol;
    
    @Getter
    @Setter
    private HtmlDataTable dataTable;
    
    @Getter
    @Setter
    private HtmlInputHidden dataItemId = new HtmlInputHidden(); 

    public List<Policy> getPoliciesByCustomer(){
        this.policies = polService.getPoliciesByCustomer(custModel.getCustomer());
        return policies;
    }
    
    public List<PolicyType> getPolicyTypes(){
        List<PolicyType> polTypes = poltypeService.getPolicyTypes();
        return polTypes;
    }
    
    public Policy createPolicy(){
             this.policy = polService.newPolicy(new PolicyApplicationDTO(custModel.getFirstname(),
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
        if(this.policy != null){
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
    
    public void init(){
        this.policyTypes = this.getPolicyTypes();
        polService.checkPolicies();
    }
}
