package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DamageType implements Serializable {
    @Id @GeneratedValue private long damageTypeID;
    private String name;

    public DamageType(){
    }
    
    public DamageType(String name){
        this.name = name;
    }
    
    public long getDamageTypeID() {
        return damageTypeID;
    }

    public void setDamageTypeID(long damageTypeID) {
        this.damageTypeID = damageTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.damageTypeID ^ (this.damageTypeID >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DamageType other = (DamageType) obj;
        if (this.damageTypeID != other.damageTypeID) {
            return false;
        }
        return true;
    }
    
    
}
