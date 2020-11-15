package org.projet_iwa.alert.api.model;

public class AlertResponse extends Response<AlertResponseType, AlertDTO> {

    public AlertResponse(AlertResponseType alertResponseType){
        super(alertResponseType, null);
    }

//    public AlertResponse(AlertResponseType alertResponseType, AlertDTO alertDTO){
//        super(alertResponseType, alertDTO);
//    }
}
