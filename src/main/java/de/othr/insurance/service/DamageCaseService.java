package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.DamageCase;
import de.othr.insurance.entity.DamageType;
import de.othr.insurance.entity.Policy;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionDamageCase;

@RequestScoped
@WebService
public class DamageCaseService implements Serializable{
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    
    @Inject
    CustomerService custServ;
    @Inject
    BankService bank;
    @Inject
    @OptionDamageCase
    private Logger logger;
    
    @Transactional
    public DamageCase getDamageCase(long damageCaseID){
        Query q = entityManager.createQuery("Select dc FROM DamageCase as dc WHERE dc.damageCaseId= :damageCaseId");
        q.setParameter("damageCaseId", damageCaseID);
        List<DamageCase> damageCases = q.getResultList();
        DamageCase dc = damageCases.get(0);
        return dc;
    }
    
    @Transactional
    public List<DamageCase> getDamageCaseByCustomer (Customer customer){
        Query q = entityManager.createQuery("Select dc FROM DamageCase as dc WHERE dc.custID = :customer",DamageCase.class);
        q.setParameter("customer", customer);
        List<DamageCase> damageCases = q.getResultList();
        return damageCases;
    }

    @Transactional
    public DamageCase newDamagecase(String description, Policy policy, DamageType damagetype, Customer customer, double costs){
        double selfpart = policy.getPolicyTypeID().getSelfparticipation();
        DamageCase newDC = new DamageCase(description, damagetype, policy, customer,costs, costs-costs*selfpart);
        double refund = costs*selfpart;
        String InsuranceIban = custServ.getCustomerByEmail("admin@admin.de").getIban();
        if(bank.doTransfer(InsuranceIban, customer.getIban(),refund)) {
            entityManager.persist(newDC);
            logger.info("new Damagecase created: " + newDC.getDescription());
            return newDC;
        } else {
            return null;
        }
    }
}
