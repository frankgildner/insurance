package de.othr.insurance.model;

import de.othr.insurance.entity.Policy;
import de.othr.insurance.service.PolicyService;
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
    
    private List<Policy> policies;
    
    public List<Policy> getPoliciesByCustomer(){
        this.policies = polService.getPoliciesByCustomer(custModel.getCustomer());
        return policies;
    }
}
