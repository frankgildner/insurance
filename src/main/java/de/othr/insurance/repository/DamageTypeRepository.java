package de.othr.insurance.repository;

import de.othr.insurance.entity.DamageType;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Frank
 */

@RequestScoped
@Transactional
public class DamageTypeRepository extends SingleIdEntityRepository<DamageType> {
    
}
