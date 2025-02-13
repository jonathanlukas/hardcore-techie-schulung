package com.camunda.consulting;

import com.camunda.consulting.JobWorkers.AnimalPicture;
import io.camunda.zeebe.spring.client.jobhandling.result.ResultProcessor;

import java.util.Base64;
import java.util.Map;

public class AnimalPictureResultProcessor implements ResultProcessor {
  private final PictureStore pictureStore;

  public AnimalPictureResultProcessor(PictureStore pictureStore) {
    this.pictureStore = pictureStore;
  }

  @Override
  public Object process(Object result) {
    AnimalPicture animalPicture = (AnimalPicture) result;
    byte[] data = Base64
        .getDecoder()
        .decode(animalPicture.animalPicture());
    pictureStore.storePicture(animalPicture.id(), data);
    return Map.of("pictureId", animalPicture.id());
  }
}
