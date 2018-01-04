package de.othr.insurance.repository;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.DamageCase;
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
public class DamageCaseRepository extends SingleIdEntityRepository<DamageCase>{
    
    public List<DamageCase> getDamageCaseByCustomer (Customer customer){
        Query q = this.getEntityManager().createQuery("Select dc FROM DamageCase as dc WHERE dc.custID = :customer",DamageCase.class);
        q.setParameter("customer", customer);
        List<DamageCase> damageCases = q.getResultList();
        return damageCases;
    }
}
