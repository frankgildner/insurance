package de.othr.insurance.converter;

import de.othr.insurance.entity.Policy;
import de.othr.insurance.service.PolicyService;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PolicyConverter implements Converter{
    
    @Inject
    PolicyService polService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null){
            return "";
        }
        Policy pol = polService.getPolicyById(Long.parseLong(value));
        if(pol==null){
            return "";
        }
        return pol;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        if(!value.getClass().equals(Policy.class)){
            return null;
        }
        return String.valueOf(((Policy)value).getId());
    }
}
