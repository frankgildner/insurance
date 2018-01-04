/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.othr.insurance.repository;

import de.othr.insurance.entity.Customer;
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
public class CustomerRepository extends SingleIdEntityRepository<Customer>{
    
    public Customer findByEmail(String email){
        Query q = this.getEntityManager().createQuery("Select c FROM Customer as c WHERE c.email= :email");
        q.setParameter("email",email);
        List<Customer> customers = q.getResultList();
        if (customers.isEmpty()){
            return null;
        } else {
            return customers.get(0);
        }
    }
}
