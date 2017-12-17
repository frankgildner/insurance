package de.othr.insurance.model;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Util;
import de.othr.insurance.service.CustomerService;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class customerModel implements Serializable{
    
    @Inject
    private CustomerService custService;
    
     private String email;
    private String firstname;
    private String lastname;
    private String iban;
    private String password;
    private String password2;
    private Date birthday;
    private String street;
    private int postCode;
    private String city;
    private String country;
    private Customer customer;
    private String errorMsg;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public String getFirstName() {
        return this.customer.getPrename();
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
    
    public String signup(){
        this.errorMsg = "";
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
                this.customer = custService.login(customer.getEmail(), customer.getPassword());
                if(this.customer != null && this.customer.getEmail() != null){
                    HttpSession session = Util.getSession();
                    session.setAttribute("user", this.customer);
                        return "profile";
                    }  else {   
                    return "login";
                    }
            } else {
                return "register";
            }
        } else {
            this.errorMsg = "Please fill all inputs!";
            return "register";
        }
    }
}
