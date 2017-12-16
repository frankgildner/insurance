package de.othr.insurance.model;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Util;
import de.othr.insurance.service.CustomerService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginModel implements Serializable{
    
    @Inject
    private CustomerService custService;
    
    private String email;
    private String password;
    private String message;
    private Customer customer;

    public String getMessage() {
        return message;
    }
    
    public String getFirstName() {
        return this.customer.getPrename();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String validateCredentials(){
     this.customer = custService.login(email, password);
     if(this.customer != null && this.customer.getEmail() != null){
         HttpSession session = Util.getSession();
         session.setAttribute("user", this.customer);
         return "profile";
     }  else {
        //FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please try again!"));   
        return "login";
        }
    }
    
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }
    
}
