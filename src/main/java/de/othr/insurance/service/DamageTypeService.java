package de.othr.insurance.service;

import de.othr.insurance.entity.DamageType;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
@WebService
public class DamageTypeService {
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;
    
    @Transactional
    public List<DamageType> getDamageTypes(){
        Query q = entityManager.createQuery("Select dt from DamageType dt", DamageType.class);
        List<DamageType> allDamTypes = q.getResultList();
        return allDamTypes;
    }
    @Transactional 
    public DamageType getDamageTypeById(long id){
        Query q = entityManager.createQuery("Select dt from DamageType as dt where dt.damageTypeID=:id",DamageType.class);
        q.setParameter("id",id);
        List<DamageType> list = q.getResultList();
        DamageType damType = list.get(0);
        return damType;
    }
}
