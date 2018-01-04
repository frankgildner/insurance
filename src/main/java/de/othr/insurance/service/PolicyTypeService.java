package de.othr.insurance.service;

import de.othr.insurance.entity.PolicyType;
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
public class PolicyTypeService {
    @PersistenceContext(unitName="insurancePU")
    private EntityManager entityManager;

    @Transactional
    public List<PolicyType> getPolicyTypes(){
        Query q = entityManager.createQuery("Select pt from PolicyType pt", PolicyType.class);
        List<PolicyType> all = q.getResultList();
        return all;
    }
    @Transactional
    public PolicyType getPolicyTypeById(long id){
        Query q = entityManager.createQuery("Select pt from PolicyType as pt where pt.id=:id",PolicyType.class);
        q.setParameter("id",id);
        List<PolicyType> list = q.getResultList();
        System.out.println("found list"+list);
        PolicyType found = list.get(0);
        System.out.println(found);
        return found;
    }
    
    @Transactional
    public PolicyType createPolicyType(String name, double selfParticipation, int costsPerDay){
        PolicyType newPT = new PolicyType(name,selfParticipation,costsPerDay);
        entityManager.persist(newPT);
        return newPT;
    }
    
}
