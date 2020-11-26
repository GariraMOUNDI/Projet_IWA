package org.projet_iwa.gateway.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.projet_iwa.gateway.api.model.Response;
import org.projet_iwa.gateway.api.service.MicroserviceWebRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MicroserviceWebRequestTest {

    private MicroserviceWebRequest microserviceWebRequest;

    @Test
    @DisplayName("Response Testing")
    public void createUser() {
        Response response = microserviceWebRequest.createUser(SampleData.getSampleDTO());
        assertThat("?").isEqualTo(response.getType());
        assertThat(response.getPayload()).isNull();
    }
    public void sendLocation() {
        Response response = microserviceWebRequest.sendLocation(SampleData.getSampleDTO());
        assertThat("?").isEqualTo(response.getType());
        assertThat(response.getPayload()).isNull();
    }

}
