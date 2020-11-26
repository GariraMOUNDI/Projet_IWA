package org.projet_iwa.gateway.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class MicroserviceRequestInit {

    @Value("${server.user-url}")
    private String user_url;

    @Value("${server.location-url}")
    private String location_url;

    private RestTemplateBuilder baseRequest(String url){
        return new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    protected RestTemplate userRequestWithoutAuthentication(){
        return baseRequest(user_url)
                .build();
    }

    protected RestTemplate userRequestWithAuthentication(String token){
        return baseRequest(user_url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .build();
    }

    protected RestTemplate locationRequestWithAuthentication(String token){
        return baseRequest(location_url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .build();
    }
}
