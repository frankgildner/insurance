package de.othr.insurance.service;

import de.othr.insurance.entity.Address;
import de.othr.insurance.entity.Customer;
import de.othr.insurance.repository.CustomerRepository;
import utils.BCrypt;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionCustomer;

@RequestScoped
@WebService
public class CustomerService {
    
    private static final int workload = 12;
    
    @Inject
    private CustomerRepository custRep;
    
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
        custRep.persist(c);
        
        logger.info("new user created: " + c.getEmail());
        
        return c;
        }
        
    }
    
    @Transactional
    public Customer login(String email, String password){
        Customer c = custRep.findByEmail(email);
        if(c == null){
            return null;
        } else {
            if(BCrypt.checkpw(password, c.getPassword())){
                return c;
            } else {
                return null;
            }
        }
        
    }
    
    @Transactional
    public String deleteCustomer(Customer customer){
        custRep.remove(customer.getId());
        logger.info("user successfully deleted");
        return "Customer successfully deleted";
    }
    
    @Transactional
    public Customer getCustomerByEmail(String email){
        return custRep.findByEmail(email);
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
