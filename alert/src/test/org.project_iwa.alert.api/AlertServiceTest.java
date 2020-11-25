@AutoConfigureMockMvc
@SpringBootTest
public class AlertServiceTest {
    @Autowired
    private AlertService alertService;

    @Test
    @DisplayName("Send alert without error")
    public void sendAlert(){
        AlertResponse response = alertService.sendAlert(SampleData.getSampleDTO());
        assertThat(AlertResponseType.ALERT_SEND).isEqualTo(response.getType());
        assertThat(response.getPayload()).isNull();
    }
}