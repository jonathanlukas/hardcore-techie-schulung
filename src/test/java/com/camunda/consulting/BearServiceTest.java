package com.camunda.consulting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BearServiceTest {
  @Test
  public void testBearService() {
    BearService bearService = new BearService();
    byte[] bear = bearService.getBear(200, 200);
    assertThat(bear).isNotNull().isNotEmpty();
  }
}
