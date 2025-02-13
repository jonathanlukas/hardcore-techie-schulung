package com.camunda.consulting;

import com.camunda.consulting.JobWorkers.AnimalPicture;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.jobhandling.parameter.ParameterResolver;

import java.util.Base64;

public class AnimalPictureParameterResolver implements ParameterResolver {
  private final PictureStore pictureStore;
  public AnimalPictureParameterResolver(PictureStore pictureStore) {
    this.pictureStore = pictureStore;
  }

  @Override
  public Object resolve(JobClient jobClient, ActivatedJob job) {
    String pictureId = (String) job.getVariable("pictureId");
    String picture = Base64
        .getEncoder()
        .encodeToString(pictureStore.getPicture(pictureId));
    return new AnimalPicture(picture, pictureId);
  }
}
