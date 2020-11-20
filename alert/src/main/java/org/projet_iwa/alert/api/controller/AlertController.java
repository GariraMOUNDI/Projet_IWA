package org.projet_iwa.alert.api.controller;

import org.projet_iwa.alert.api.model.AlertDTO;
import org.projet_iwa.alert.api.model.Response;
import org.projet_iwa.alert.api.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private IAlertService iAlertService;

    // For test only
    @PostMapping
    public Response<?, ?> sendAlert(@RequestBody AlertDTO alertDTO){
        return iAlertService.sendAlert(alertDTO);
    }

}
