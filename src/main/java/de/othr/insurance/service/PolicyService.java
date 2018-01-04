package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.entity.PolicyApplicationDTO;
import de.othr.insurance.repository.PolicyRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionPolicy;

@RequestScoped
@WebService
public class PolicyService implements Serializable{

    @Inject
    CustomerService custServ;
    
    @Inject
    PolicyTypeService polServ;
    
    @Inject
    BankService bank;
    
    @Inject
    @OptionPolicy
    private Logger logger;
    
    @Inject
    PolicyRepository polRep;
    
    @Transactional
    public Policy newPolicy(PolicyApplicationDTO p){
        long custID = 0;
        Customer c = custServ.getCustomerByEmail(p.getEmail());
        if(c != null){
            custID = c.getId();
        } else {
            c = custServ.signup(p.getEmail(), 
                    p.getPrename(), 
                    p.getSurname(), 
                    p.getBirthdate(), 
                    p.getIban(), 
                    p.getStreet(), 
                    p.getPostalCode(), 
                    p.getCity(), 
                    p.getCountry(), 
                    p.getPassword());
        }
        Policy newP = new Policy(p.getStartDate(),
                p.getDuration(),
                p.getItemID(),
                this.calculatePrice(p.getDuration(), p.getPolicyType()),
                c,
                p.getPolicyType()
        );
        String InsuranceIban = custServ.getCustomerByEmail("admin@admin.de").getIban();
        if(bank.doTransfer(c.getIban(),InsuranceIban,newP.getPrice())) {
            polRep.persist(newP);
            logger.info("new policy created: (id)" + newP.getId());
            return newP;
        } else {
            return null;
        }
    }
    
    @Transactional
    public List<Policy> getPoliciesByCustomer(Customer customer){
       return polRep.getPoliciesByCustomer(customer);
    }
    
    @Transactional
    public double calculatePrice(int duration, PolicyType polType){
        double price = duration*polType.getPricePerDay();
        return price;
    }
    @Transactional
    public List<PolicyType> getPolicyTypes(){
        return polServ.getPolicyTypes();
    }
    @Transactional
    public void cancelPolicy(Policy policy){
        Policy p = polRep.findById(policy.getId());
        p.setStatus("canceled");
    }
    
    @Transactional
    public Policy getPolicy(long policyID){
        return polRep.findById(policyID);
    }
    
    @Transactional
    public Policy getPolicyById(long policyID){
        return polRep.findById(policyID);
    }
}
