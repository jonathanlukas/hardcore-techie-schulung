package com.camunda.consulting;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobWorkers {
  private static final Logger LOG = LoggerFactory.getLogger(JobWorkers.class);

  private final BearService bearService;

  public JobWorkers(BearService bearService) {
    this.bearService = bearService;
  }

  @JobWorker
  public void dogPictureFetching(){
    LOG.info("Dog Picture Fetching");
  }

  @JobWorker
  public void catPictureFetching(){
    LOG.info("Cat Picture Fetching");
  }

  @JobWorker
  public void bearPictureFetching(@VariablesAsType PictureInfo pictureInfo){
    LOG.info("Bear Picture Fetching");
    bearService.getBear(pictureInfo.height(), pictureInfo.width());
  }

  public record PictureInfo(Integer height, Integer width){}
}
