package de.othr.insurance.converter;

import de.othr.insurance.entity.DamageType;
import de.othr.insurance.service.DamageCaseService;
import de.othr.insurance.service.DamageTypeService;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DamageTypeConverter implements Converter {
    @Inject
    DamageCaseService dcServ;
    @Inject
    DamageTypeService dtServ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value == null){
           return "";
       }
       DamageType damType = dtServ.getDamageTypeById(Long.parseLong(value));
       if(damType == null){
           return "";
       }
       return damType;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        if(!value.getClass().equals(DamageType.class)){
            return null;
        }
        return String.valueOf(((DamageType)value).getDamageTypeID());
    }
    
}
