package com.camunda.consulting;

import io.camunda.process.test.api.CamundaAssert;
import io.camunda.process.test.api.CamundaSpringProcessTest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "operate.client.enabled=false", "tasklist.client.enabled=false" })
@CamundaSpringProcessTest
public class AppTest {
  @Autowired
  ZeebeClient zeebeClient;

  @ParameterizedTest
  @CsvSource({"dog, Fetch dog picture", "cat, Fetch cat picture", "bear, Fetch bear picture"})
  void shouldExecuteProcess(String animalKind, String activityName) {
    ProcessInstanceEvent processInstance = zeebeClient
        .newCreateInstanceCommand()
        .bpmnProcessId("AnimalPictureFetcherProcess")
        .latestVersion()
        .variable("animalKind", animalKind)
        .send()
        .join();
    CamundaAssert.assertThat(processInstance).isCompleted().hasCompletedElements(activityName);
  }
}
