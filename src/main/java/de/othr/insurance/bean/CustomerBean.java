package de.othr.insurance.bean;

import de.othr.insurance.entity.Customer;
import utils.Util;
import de.othr.insurance.service.BankService;
import de.othr.insurance.service.CustomerService;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class CustomerBean implements Serializable{
    
    @Inject
    CustomerService custService;
    
    @Inject
    BankService bankServ;
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String firstname;
    
    @Getter
    @Setter
    private String lastname;
    
    @Getter
    @Setter
    private String iban;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private String password2;
    
    @Getter
    @Setter
    private Date birthday;
    
    @Getter
    @Setter
    private String street;
    
    @Getter
    @Setter
    private int postCode;
    
    @Getter
    @Setter
    private String city;
    
    @Getter
    @Setter
    private String country;
    
    @Getter
    @Setter
    private Customer customer;
    
    @Getter
    @Setter
    private Pattern pattern;
    
    @Getter
    @Setter
    private Matcher matcher;
    
    @Getter
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public String validateCredentials(){
     this.customer = custService.login(email, password);
     if(this.customer != null && this.customer.getEmail() != null){
         HttpSession session = Util.getSession();
         session.setAttribute("user", this.customer);
         return "profile";
     }  else {
        FacesContext.getCurrentInstance().addMessage("loginForm:loginVal",new FacesMessage("Invalid E-Mail or Password! Please try again!"));   
        return null;
        }
    }
    
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        this.customer = null;
        return "login";
    }
    
    public String signup(){
        if(!this.email.equals("") && 
            !this.firstname.equals("") && 
            !this.lastname.equals("") &&
            !this.iban.equals("") &&
            !this.password.equals("") && 
            !this.password2.equals("") &&
            this.birthday != null &&
            !this.street.equals("") &&
            this.postCode > 0 &&
            !this.city.equals("") &&
            !this.country.equals("")) {
            
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(this.email);
            if(matcher.matches()){
                if(this.password.equals(this.password2)){
                    if(bankServ.checkIban(this.iban)){
                            this.customer = custService.signup(this.email, 
                            this.firstname, 
                            this.lastname, 
                            this.birthday, 
                            this.iban, 
                            this.street, 
                            this.postCode, 
                            this.city, 
                            this.country, 
                            this.password);
                        if(this.customer != null){
                            this.customer = custService.login(customer.getEmail(), this.password);
                            if(this.customer != null && this.customer.getEmail() != null){
                                HttpSession session = Util.getSession();
                                session.setAttribute("user", this.customer);
                                    return "profile";
                                }  else {   
                                    return "login";
                                }
                        } else {
                            // Email exists
                            FacesContext.getCurrentInstance().addMessage("registerForm:registerVal",new FacesMessage("E-Mail is already existing! Please use another one!")); 
                            return null;
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage("registerForm:registerVal",new FacesMessage("Please type in a right IBAN! If you do not have one, please sign up at the bank!"));
                        return null;
                    }
                } else {
                    // Passwords do not match
                    FacesContext.getCurrentInstance().addMessage("registerForm:registerVal",new FacesMessage("Passwords do not match! Please check!"));
                    return null;
                }
            } else {
                // Email is not a normal email
                FacesContext.getCurrentInstance().addMessage("registerForm:registerVal",new FacesMessage("Invalid email format")); 
                return null;
            }          
        } else {
            // Not all inputs are filled
            FacesContext.getCurrentInstance().addMessage("registerForm:registerVal",new FacesMessage("Please fill all inputs!")); 
            return null;
        }
    }
    
    public boolean adminIsLoggedIn(){
        if(this.customer != null){
            if (this.customer.getEmail().equals("admin@admin.de")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean isLoggedIn(){
        return this.customer != null;
    }
}