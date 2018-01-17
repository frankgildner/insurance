package de.othr.insurance.service;

import de.oth.gmeiner.swgmeiner.service.TransferService_Service;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.xml.ws.WebServiceRef;
import de.oth.gmeiner.swgmeiner.service.TransferService;
import de.oth.gmeiner.swgmeiner.service.Transfer;
import de.oth.gmeiner.swgmeiner.service.Customer;

@RequestScoped
@WebService
public class BankService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/im-lamport_8080/SWGmeiner-1.0/transferService.wsdl")
    private TransferService_Service service;
    private TransferService port;
    private Transfer result;
    private Customer customer;

        
    @Transactional
    public boolean doTransfer(String transmitter, String receiver, double amount, String purpose){
        try {
        port = service.getTransferServicePort();
        result = port.createTransfer(transmitter, receiver, amount, purpose);
        } catch(Exception ex) {
            throw new RuntimeException("Error: Could not create Transfer "+ex.getMessage(), ex);
        }
        return result != null;
    }
    
    @Transactional
    public boolean checkIban(String iban){
        try {
        port = service.getTransferServicePort();
        System.out.println("iban: "+iban);
        customer = port.checkIban(iban);
        } catch( Exception ex) {
            throw new RuntimeException("Error: Could not check Iban "+ex.getMessage(), ex);
        }
        return customer != null;
    }
}
