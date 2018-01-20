package utils;

import de.othr.insurance.service.BankService;
import de.othr.insurance.service.CustomerService;
import de.othr.insurance.service.DamageCaseService;
import de.othr.insurance.service.DamageTypeService;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionBank;
import utils.qualifiers.OptionCustomer;
import utils.qualifiers.OptionDamageCase;
import utils.qualifiers.OptionDamageType;
import utils.qualifiers.OptionPolicy;
import utils.qualifiers.OptionPolicyType;

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
    
    @Produces
    @ApplicationScoped
    @OptionPolicyType
    public Logger policyTypeLogger () {  
        return LogManager.getLogger(PolicyTypeService.class); 
    }  
    
    @Produces
    @ApplicationScoped
    @OptionDamageType
    public Logger damageTypeLogger () {  
        return LogManager.getLogger(DamageTypeService.class); 
    }
    @Produces
    @ApplicationScoped
    @OptionBank
    public Logger bankLogger() {
        return LogManager.getLogger(BankService.class);
    }
    
}
