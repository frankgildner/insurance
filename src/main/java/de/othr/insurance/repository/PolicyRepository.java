package de.othr.insurance.repository;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Policy;
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
public class PolicyRepository extends SingleIdEntityRepository<Policy> {
    
    public List<Policy> getPoliciesByCustomer(Customer customer){
        Query q = this.getEntityManager().createQuery("Select p FROM Policy as p WHERE p.custID= :customer",Policy.class);
        q.setParameter("customer",customer);
        List<Policy> policies = q.getResultList();
        return policies;
    }
}
