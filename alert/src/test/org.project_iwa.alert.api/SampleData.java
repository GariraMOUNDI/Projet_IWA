package org.projet_iwa.alert.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.projet_iwa.alert.api.model.AlertDTO;

public class SampleData {

    private static final ObjectMapper map = new ObjectMapper();

    private static final AlertFactory alertFactory = new AlertFactory();

    public static AlertDTO getSampleDTO(){
        AlertDTO dto = new AlertDTO(
                null,
                null,
                "parti@gazert.com"
        );
        return dto;
    }

    public static String toJson(AlertDTO s) throws JsonProcessingException {
        return map.writeValueAsString(s);
    }

    public static Alert getSampleAlert(){
            return alertFactory.createAlertModel(getSampleDTO());
        }