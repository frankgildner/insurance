package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Policy;
import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.entity.PolicyApplicationDTO;
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

@RequestScoped
@WebService
public class PolicyService implements Serializable{
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    @Inject
    CustomerService custServ;
    
    @Transactional
    public Policy newPolicy(PolicyApplicationDTO p){
        long custID = 0;
        Customer c = custServ.getCustomerByEmail(p.getEmail());
        if(c != null){
            custID = c.getCustID();
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
        entityManager.persist(newP);
        return newP;
    }
    @Transactional
    public List<Policy> getPoliciesByCustomer(Customer customer){
        long custID = customer.getCustID();
        Query q = entityManager.createQuery("Select p FROM Policy as p WHERE p.custID= :customer",Policy.class);
        q.setParameter("customer",customer);
        List<Policy> policies = q.getResultList();
        return policies;
    }
    @Transactional
    public double calculatePrice(int duration, PolicyType polType){
        double price = duration*polType.getPricePerDay();
        return price;
    }
}
