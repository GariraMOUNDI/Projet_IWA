package org.projet_iwa.alert.api;

@AutoConfigureMockMvc
@SpringBootTest
public class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAlertService ialertService;

    @Test
    public void sendAlert() throws Exception {
        doReturn(new AlertResponse(AlertResponseType.ALERT_SEND))
                .when(iAlertService).sendAlert(any());

        mockMvc.perform(
                post("/alert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(SampleData.toJson(SampleData.getSampleDTO()))
        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value(AlertResponseType.ALERT_SEND.toString()))
                .andExpect(jsonPath("$.payload").isEmpty());

    }
}