package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.DamageCase;
import de.othr.insurance.entity.DamageType;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
@WebService
public class DamageCaseService implements Serializable{
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    
    @Transactional
    public DamageCase getDamageCase(long damageCaseID){
        Query q = entityManager.createQuery("Select dc FROM DamageCase as dc WHERE dc.damageCaseId= :damageCaseId");
        q.setParameter("damageCaseId", damageCaseID);
        List<DamageCase> damageCases = q.getResultList();
        DamageCase dc = damageCases.get(0);
        return dc;
    }
    
    @Transactional
    public List<DamageCase> getDamageCaseByCustomer (long customerID){
        Query q = entityManager.createQuery("Select dc FROM DamageCase as dc WHERE dc.custID = :customerID");
        q.setParameter("customerID", customerID);
        List<DamageCase> damageCases = q.getResultList();
        return damageCases;
    }

    @Transactional
    public void newDamagecase(String description, long policyNr, DamageType damagetype, Customer customer, double costs){
        DamageCase neu = new DamageCase(description, damagetype, policyNr, customer,costs);
    }
}
