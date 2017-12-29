package de.othr.insurance.converter;

import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PolicyTypeConverter implements Converter{
    
    @Inject
    PolicyService polService;
    @Inject 
    PolicyTypeService poltypeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null){
            return "";
        }
        System.out.println(value);
                System.out.println(Long.parseLong(value));
        PolicyType polT = poltypeService.getPolicyTypeById(Long.parseLong(value));
        if(polT==null){
            return "";
        }
        return polT;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        if(!value.getClass().equals(PolicyType.class)){
            return null;
        }
        return String.valueOf(((PolicyType)value).getPolicyTypeID());
    }
}
