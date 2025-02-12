package com.camunda.consulting;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobWorkers {
  private static final Logger LOG = LoggerFactory.getLogger(JobWorkers.class);

  @JobWorker
  public void dogPictureFetching(){
    LOG.info("Dog Picture Fetching");
  }

  @JobWorker
  public void catPictureFetching(){
    LOG.info("Cat Picture Fetching");
  }

  @JobWorker
  public void bearPictureFetching(){
    LOG.info("Bear Picture Fetching");
  }
}
