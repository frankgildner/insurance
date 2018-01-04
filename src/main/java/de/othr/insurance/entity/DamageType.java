package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class DamageType extends BaseEntity implements Serializable {

    @Getter
    @Setter
    private String name;

    public DamageType(){
    }
    
    public DamageType(String name){
        this.name = name;
    }
}
