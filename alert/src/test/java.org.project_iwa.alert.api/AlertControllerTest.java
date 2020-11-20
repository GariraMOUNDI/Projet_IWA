package org.projet_iwa.alert.api;

@AutoConfigureMockMvc
@SpringBootTest
public class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAlertService ialertService;

    @Test
    public void createAlert() throws Exception {
        when(ialertService.createAlert(SampleData.getSampleDTO()))
                .thenReturn(null);

        mockMvc.perform(
                post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SampleData.toJson(SampleData.getSampleDTO())))
                .andDo(print())
                .andExpect(status().isOk());
    }
}