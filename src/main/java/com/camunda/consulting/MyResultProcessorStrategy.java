package com.camunda.consulting;

import com.camunda.consulting.JobWorkers.AnimalPicture;
import io.camunda.zeebe.spring.client.jobhandling.result.DefaultResultProcessorStrategy;
import io.camunda.zeebe.spring.client.jobhandling.result.ResultProcessor;
import io.camunda.zeebe.spring.client.jobhandling.result.ResultProcessorStrategy;

public class MyResultProcessorStrategy extends DefaultResultProcessorStrategy {
  private final PictureStore pictureStore;

  public MyResultProcessorStrategy(PictureStore pictureStore) {
    this.pictureStore = pictureStore;
  }

  @Override
  public ResultProcessor createProcessor(Class<?> resultType) {
    if(AnimalPicture.class.isAssignableFrom(resultType)) {
      return new AnimalPictureResultProcessor(pictureStore);
    }
    return super.createProcessor(resultType);
  }
}
