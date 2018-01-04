package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class DamageType extends BaseEntity implements Serializable {

    private String name;

    public DamageType(){
    }
    
    public DamageType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
