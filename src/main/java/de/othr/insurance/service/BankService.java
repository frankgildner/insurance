package de.othr.insurance.service;

import de.oth.gmeiner.swgmeiner.service.TransferService_Service;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.xml.ws.WebServiceRef;
import de.oth.gmeiner.swgmeiner.service.TransferService;

@RequestScoped
@WebService
public class BankService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/im-lamport_8080/SWGmeiner-1.0/transferService.wsdl")
    private TransferService_Service service;
    private TransferService port;

        
    @Transactional
    public String doTransfer(){
        
// Call Web Service Operation
            port = service.getTransferServicePort();
            // TODO process result here
            //checkiban(string iban) customer wenn true null wenn nicht vorhanden
            //sender empfänger betrag (empfänger bin immer ich)
            de.oth.gmeiner.swgmeiner.service.Transfer result = port.createTransfer("DE88549012303253", "DE65556666370676", 20.1);
            System.out.println("Result = "+result);
        if(result == null){
            return null;
        } else {
            return "signup";
        }
    }
}
