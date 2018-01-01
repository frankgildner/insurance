package de.othr.insurance.converter;

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
       
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
    }
    
}
