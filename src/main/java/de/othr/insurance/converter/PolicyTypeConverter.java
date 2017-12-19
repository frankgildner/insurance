package de.othr.insurance.converter;

import de.othr.insurance.entity.PolicyType;
import de.othr.insurance.service.PolicyService;
import de.othr.insurance.service.PolicyTypeService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("de.othr.insurance.converter.PolicyTypeConverter")
public class PolicyTypeConverter implements Converter{
    
    @Inject
    private PolicyService polService;
    @Inject 
    PolicyTypeService poltypeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null){
            return "";
        }
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
