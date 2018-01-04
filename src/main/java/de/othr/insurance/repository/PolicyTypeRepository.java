package de.othr.insurance.repository;

import de.othr.insurance.entity.PolicyType;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Frank
 */
@RequestScoped
@Transactional
public class PolicyTypeRepository extends SingleIdEntityRepository<PolicyType>{
    
}
