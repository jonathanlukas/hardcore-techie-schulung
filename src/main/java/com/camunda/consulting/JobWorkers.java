package com.camunda.consulting;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Component
public class JobWorkers {
  private static final Logger LOG = LoggerFactory.getLogger(JobWorkers.class);

  private final BearService bearService;
  private final CatService catService;

  public JobWorkers(BearService bearService, CatService catService) {
    this.bearService = bearService;
    this.catService = catService;
  }

  @JobWorker
  public void dogPictureFetching(){
    LOG.info("Dog Picture Fetching");
  }

  @JobWorker
  public AnimalPicture catPictureFetching(){
    LOG.info("Cat Picture Fetching");
    byte[] cat = catService.getCat();
    return fromByteArray(cat);
  }

  @JobWorker
  public AnimalPicture bearPictureFetching(@VariablesAsType PictureInfo pictureInfo){
    LOG.info("Bear Picture Fetching");
    byte[] bear = bearService.getBear(pictureInfo.height(), pictureInfo.width());
    return fromByteArray(bear);
  }

  private AnimalPicture fromByteArray(byte[] bytes) {
    String base64Bear = Base64
        .getEncoder()
        .encodeToString(bytes);
    return new AnimalPicture(base64Bear, UUID.randomUUID().toString());
  }

  public record PictureInfo(Integer height, Integer width){}
  public record AnimalPicture(String animalPicture, String id) {}
}
