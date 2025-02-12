package com.camunda.consulting;

import io.camunda.process.test.api.CamundaSpringProcessTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "operate.client.enabled=false","tasklist.client.enabled=false"})
@CamundaSpringProcessTest
public class AppTest {
  @Test
  void contextLoads() {}
}
