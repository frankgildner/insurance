package de.othr.insurance.repository;

import de.othr.insurance.entity.PolicyType;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Frank
 */
@RequestScoped
@Transactional
public class PolicyTypeRepository extends SingleIdEntityRepository<PolicyType>{

    public PolicyType getPolicyTypeByName(String name){
        Query q = this.getEntityManager().createQuery("Select pt FROM PolicyType as pt WHERE pt.name= :name",PolicyType.class);
        q.setParameter("name",name);
        List<PolicyType> types = q.getResultList();
        PolicyType pt = types.get(0);
        
        return pt;
    }
}
