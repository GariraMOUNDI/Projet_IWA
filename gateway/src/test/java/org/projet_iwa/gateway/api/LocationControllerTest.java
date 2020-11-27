//package org.projet_iwa.gateway.api;
//
//import org.junit.jupiter.api.Test;
//import org.projet_iwa.gateway.api.service.MicroserviceWebRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.ws.rs.core.MediaType;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class LocationControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MicroserviceWebRequest microserviceWebRequest;
//
//    @Test
//    public void sendLocation() throws Exception {
//        doReturn("Response Location")
//                .when(microserviceWebRequest).createUser(any());
//
//        mockMvc.perform(
//                post("/location")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(SampleData.toJson(SampleData.getSampleDTO()))
//        ).andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.type").value("?"))
//                .andExpect(jsonPath("$.payload").value("?"));
//
//    }
//}
