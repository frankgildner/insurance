package utils;

import de.othr.insurance.service.CustomerService;
import de.othr.insurance.service.DamageCaseService;
import de.othr.insurance.service.PolicyService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionCustomer;
import utils.qualifiers.OptionDamageCase;
import utils.qualifiers.OptionPolicy;

/**
 *
 * @author Frank
 */
@ApplicationScoped
public class CustomLogger {

    @Produces
    @ApplicationScoped
    @OptionCustomer
    public Logger customerLogger () {  
        return LogManager.getLogger(CustomerService.class); 
    }  
    
    @Produces
    @ApplicationScoped
    @OptionDamageCase
    public Logger damageCaseLogger () {  
        return LogManager.getLogger(DamageCaseService.class); 
    }  
    
    @Produces
    @ApplicationScoped
    @OptionPolicy
    public Logger policyLogger () {  
        return LogManager.getLogger(PolicyService.class); 
    }  
    
}
