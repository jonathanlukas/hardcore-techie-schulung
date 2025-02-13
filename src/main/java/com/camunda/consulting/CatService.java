package com.camunda.consulting;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {
  private static final String url = "https://cataas.com/cat";

  public byte[] getCat() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url, byte[].class);
  }
}
