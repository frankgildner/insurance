package de.othr.insurance.service;

import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.repository.PolicyTypeRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;

@RequestScoped
@WebService
public class PolicyTypeService implements Serializable{

    @Inject
    PolicyTypeRepository ptRep;
    
    @Transactional
    public List<PolicyType> getPolicyTypes(){
        return ptRep.findAll();
    }
    @Transactional
    public PolicyType getPolicyTypeById(long id){
       return ptRep.findById(id);
    }
    
    @Transactional
    public PolicyType createPolicyType(String name, double selfParticipation, int costsPerDay){
        PolicyType newPT = new PolicyType(name,selfParticipation,costsPerDay);
        ptRep.persist(newPT);
        return newPT;
    }
    
}
