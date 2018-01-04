package de.othr.insurance.service;

import de.othr.insurance.entity.Address;
import de.othr.insurance.entity.Customer;
import utils.BCrypt;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionCustomer;

@RequestScoped
@WebService
public class CustomerService {
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    private static int workload = 12;
    
    @Inject
    @OptionCustomer
    private Logger logger;
    
    @Transactional
    public Customer signup(String email, String firstname, String lastname, Date birthday, String iban, String street, int postCode, String city, String country, String password){
        if(this.getCustomerByEmail(email) != null){
            return null;
        } else {
            String hashedPW = this.hashPassword(password);
            Customer c = new Customer(email,
                firstname,
                lastname, 
                new Address(street,
                    postCode,
                    city,
                    country),
                iban,
                birthday,
                hashedPW);
        entityManager.persist(c);
        
        logger.info("new user created: " + c.getEmail());
        
        return c;
        }
        
    }
    
    @Transactional
    public Customer login(String email, String password){
        Query q = entityManager.createQuery("Select c FROM Customer as c WHERE c.email= :email");
        q.setParameter("email",email);
        List<Customer> customers = q.getResultList();
        if(customers.isEmpty()){
            return null;
        } else {
            Customer c = customers.get(0);
            if(BCrypt.checkpw(password, c.getPassword())){
                return c;
            } else {
                return null;
            }
        }
        
    }
    
    @Transactional
    public String deleteCustomer(Customer customer){
        Customer c = entityManager.find(Customer.class,customer.getId());
        entityManager.remove(c);
        logger.info("user successfully deleted");
        return "Customer successfully deleted";
    }
    
    @Transactional
    public Customer getCustomerByEmail(String email){
        Query q = entityManager.createQuery("Select c from Customer as c where c.email = :email");
        q.setParameter("email", email);
        List<Customer> cust = q.getResultList();
        if(cust.isEmpty()){
            return null;
        } else {
            return cust.get(0);
        }
    }
    
    /*
        generate safe passwords with bcrypt
        https://gist.github.com/craSH/5217757
    */
    
    @Transactional
    public String hashPassword(String password){
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(password, salt);
        
        return hashedPassword;
    }
}
