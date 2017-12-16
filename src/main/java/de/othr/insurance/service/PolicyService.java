package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyApplicationDTO;
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
public class PolicyService implements Serializable{
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    
    @Transactional
    public Policy newPolicy(PolicyApplicationDTO polAppDTO){
        
        return null;
    }
    @Transactional
    public List<Policy> getPoliciesByCustomer(Customer customer){
        long custID = customer.getCustID();
        Query q = entityManager.createQuery("Select p FROM Policy as p WHERE p.custID= :custID",Policy.class);
        q.setParameter("custID",custID);
        List<Policy> policies = q.getResultList();
        return policies;
    }
}
