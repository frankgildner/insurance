package de.othr.insurance.service;

import de.othr.insurance.entity.DamageType;
import de.othr.insurance.repository.DamageTypeRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import utils.qualifiers.OptionDamageType;

@RequestScoped
@WebService
public class DamageTypeService {
    
    @Inject
    DamageTypeRepository dtRep;
    
    @Inject
    @OptionDamageType
    private Logger logger;
    
    @Transactional
    public List<DamageType> getDamageTypes(){
        return dtRep.findAll();
    }
    @Transactional 
    public DamageType getDamageTypeById(long id){
        return dtRep.findById(id);
    }
    
    @Transactional
    public DamageType createDamageType(String name){
        DamageType newDT = new DamageType(name);
        dtRep.persist(newDT);
        logger.info("new damagetype created: " + newDT.getName());
        return newDT;
    }
}
