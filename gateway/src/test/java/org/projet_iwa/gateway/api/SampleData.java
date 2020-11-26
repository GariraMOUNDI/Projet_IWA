 package org.projet_iwa.gateway.api;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.projet_iwa.gateway.api.model.*;

 public class SampleData {

    private static final ObjectMapper map = new ObjectMapper();

    public static Response getSampleDTO(){
            Response dto = new Response(
                    ?,
                    ?
            );
            return dto;
    }
     public static String toJson(Response response) throws JsonProcessingException {
            return map.writeValueAsString(response);
        }
    public static Alert getSampleReponse(){
                return getSampleDTO();
            }
 }