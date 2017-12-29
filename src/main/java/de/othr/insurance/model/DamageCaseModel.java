package de.othr.insurance.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class DamageCaseModel implements Serializable {
    
    @Inject 
    CustomerModel custModel;
    
}
