package de.othr.insurance.ui;

import de.othr.insurance.entity.Customer;
import de.othr.insurance.entity.Address;
import de.othr.insurance.service.CustomerService;
import de.othr.insurance.service.PolicyService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EntityTester", urlPatterns = {"/EntityTester"})
public class EntityTester extends HttpServlet {
    @Inject
    private CustomerService custService;
    private PolicyService polService;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EntityTester</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EntityTester at " + request.getContextPath() + "</h1>");

            Customer b = new Customer(1,
                    "frank",
                    "gildner",
                    new Address(new Random().nextInt(999999),"ffstraße 21",93051,"Regensburg","Deutschland"),
                    "tolle IBAN",
                    new Date(),
                    "testpassword",
                    "fg@fg.de"
            );
            //b = custService.signup(b);
            
            out.println("Customer angelegt!"+polService.getPoliciesByCustomer(b));
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
