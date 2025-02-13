package com.camunda.consulting;

import io.camunda.process.test.api.CamundaAssert;
import io.camunda.process.test.api.CamundaSpringProcessTest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Base64;

import static org.mockito.Mockito.*;

@SpringBootTest(properties = { "operate.client.enabled=false", "tasklist.client.enabled=false" })
@CamundaSpringProcessTest
public class AppTest {
  @Autowired
  ZeebeClient zeebeClient;

  @Autowired
  PictureStore pictureStore;

  @MockitoBean
  BearService bearService;

  @MockitoBean CatService catService;

  @BeforeEach
  void setup() {
    when(bearService.getBear(any(), any())).thenReturn(new byte[] { 1, 2, 3 });
    when(catService.getCat()).thenReturn(new byte[] { 1, 2, 3 });
  }

  @ParameterizedTest
  @CsvSource({ "dog, Fetch dog picture", "cat, Fetch cat picture", "bear, Fetch bear picture" })
  void shouldExecuteProcess(String animalKind, String activityName) {
    ProcessInstanceEvent processInstance = zeebeClient
        .newCreateInstanceCommand()
        .bpmnProcessId("AnimalPictureFetcherProcess")
        .latestVersion()
        .variable("animalKind", animalKind)
        .send()
        .join();
    CamundaAssert
        .assertThat(processInstance)
        .isCompleted()
        .hasCompletedElements(activityName);
  }
}
