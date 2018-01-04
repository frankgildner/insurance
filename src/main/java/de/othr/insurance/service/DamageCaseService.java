package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.DamageCase;
import de.othr.insurance.entity.DamageType;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.repository.DamageCaseRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionDamageCase;

@RequestScoped
@WebService
public class DamageCaseService implements Serializable{
    
    @Inject
    CustomerService custServ;
    
    @Inject
    BankService bank;
    
    @Inject
    @OptionDamageCase
    private Logger logger;
    
    @Inject
    DamageCaseRepository dcRep;
    
    @Transactional
    public DamageCase getDamageCase(long damageCaseID){
        return dcRep.findById(damageCaseID);
    }
    
    @Transactional
    public List<DamageCase> getDamageCaseByCustomer (Customer customer){
        return dcRep.getDamageCaseByCustomer(customer);
    }

    @Transactional
    public DamageCase newDamagecase(String description, Policy policy, DamageType damagetype, Customer customer, double costs){
        double selfpart = policy.getPolicyTypeID().getSelfparticipation();
        DamageCase newDC = new DamageCase(description, damagetype, policy, customer,costs, costs-costs*selfpart);
        double refund = costs*selfpart;
        String InsuranceIban = custServ.getCustomerByEmail("admin@admin.de").getIban();
        if(!bank.doTransfer(InsuranceIban, customer.getIban(),refund,"refund of damagecase")) {
            return null;
        } else {
            dcRep.persist(newDC);
            logger.info("new Damagecase created: " + newDC.getDescription());
            return newDC;
        }
    }
}
