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
    public boolean doTransfer(String transmitter, String receiver, double amount){
        // Call Web Service Operation
        port = service.getTransferServicePort();
        // TODO process result here
        //checkiban(string iban) customer wenn true null wenn nicht vorhanden
        //sender empfänger betrag (empfänger bin immer ich)
         result = port.createTransfer(transmitter, receiver, amount);
        if(result == null){
            return false;
        } else {
            return true;
        }
    }
    
    @Transactional
    public boolean checkIban(String iban){
        port = service.getTransferServicePort();
        System.out.println("iban: "+iban);
        customer = port.checkIban(iban);
        if(customer != null){
            return true;
        } else {
            return false;
        }
    }
}
