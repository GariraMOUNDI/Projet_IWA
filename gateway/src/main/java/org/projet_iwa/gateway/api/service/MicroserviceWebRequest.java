package org.projet_iwa.gateway.api.service;

import org.projet_iwa.gateway.api.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MicroserviceWebRequest extends MicroserviceRequestInit {

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

    public Response<?, ?> loginUser(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithoutAuthentication()
                .exchange("/login",
                        HttpMethod.POST,
                        new HttpEntity<>(body, headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }

    public Response<?, ?> confirmUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithoutAuthentication()
                .exchange("/confirmUser?token="+token,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }


    public Response<?, ?> forgotPassword(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithoutAuthentication()
                .exchange("/forgotUser",
                        HttpMethod.POST,
                        new HttpEntity<>(body, headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }


    public Response<?, ?> changeStatus(Long id, String status, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithAuthentication(token)
                .exchange("/changeStatus/"+ id +"?status="+ status,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }


    public Response<?, ?> getUserEmail(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithAuthentication(token)
                .exchange("/email/"+ id,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }

    public Response<?,?> sendLocation(String body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Response<?,?>> response = userRequestWithAuthentication(token)
                .exchange("/",
                        HttpMethod.POST,
                        new HttpEntity<>(body, headers),
                        new ParameterizedTypeReference<Response<?,?>>() {});
        return response.getBody();
    }
}
