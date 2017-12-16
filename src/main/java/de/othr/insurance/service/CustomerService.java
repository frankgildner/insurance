package de.othr.insurance.service;

import de.othr.insurance.entity.Customer;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
@WebService
public class CustomerService {
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    
    @Transactional
    public Customer signup(Customer neu){
        //anwendungslogik
        entityManager.persist(neu);
        return neu;
    }
    
    @Transactional
    public Customer login(String email, String password){
        Query q = entityManager.createQuery("Select c FROM Customer as c WHERE c.email= :email");
        q.setParameter("email",email);
        List<Customer> customers = q.getResultList();
        Customer c = customers.get(0);
        if(c.getPassword().equals(password)){
            return c;
        } else {
            return null;
        }
    }
    
    @Transactional
    public String deleteCustomer(Customer customer){
        Customer c = entityManager.find(Customer.class,customer.getCustID());
        entityManager.remove(c);
        return "Customer successfully deleted";
    }
}
