package utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author Frank
 *
 * adapted from:
 * http://www.javaknowledge.info/authentication-based-secure-login-logout-using-jsf-2-0-and-primefaces-3-4-1/
 */
 
public class Util {
 
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("user").toString();
      }
       
      public static String getUser()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("user");
        else
            return null;
      }
}