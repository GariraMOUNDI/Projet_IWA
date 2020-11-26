package org.projet_iwa.gateway.api.service;

import org.projet_iwa.gateway.api.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MicroserviceWebRequest {

    @Value("${server.user-url}")
    private String user_url;

    @Value("${server.location-url}")
    private String location_url;

    private RestTemplateBuilder baseRequest(String url){
        return new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    private RestTemplate userRequestWithoutAuthentication(){
        return baseRequest(user_url)
                .build();
    }

    private RestTemplate userRequestWithAuthentication(String token){
        return baseRequest(user_url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .build();
    }

    private RestTemplate locationRequestWithAuthentication(String token){
        return baseRequest(location_url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .build();
    }


    public Response<?, ?> createUser(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithoutAuthentication()
                .exchange("/create",
                        HttpMethod.POST,
                        new HttpEntity<>(body, headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }
}
