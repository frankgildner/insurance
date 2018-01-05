package de.othr.insurance.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="DamageType")
public class DamageType extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    public DamageType(){
    }
    
    public DamageType(String name){
        this.name = name;
    }
}
