package com.camunda.consulting;

import io.camunda.zeebe.client.api.JsonMapper;
import io.camunda.zeebe.spring.client.jobhandling.parameter.ParameterResolverStrategy;
import io.camunda.zeebe.spring.client.jobhandling.result.ResultProcessorStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  @Bean
  public ParameterResolverStrategy parameterResolverStrategy(JsonMapper jsonMapper, PictureStore pictureStore) {
    return new MyParameterResolverStrategy(jsonMapper, pictureStore);
  }

  @Bean
  public ResultProcessorStrategy resultProcessorStrategy(PictureStore pictureStore) {
    return new MyResultProcessorStrategy(pictureStore);
  }
}
