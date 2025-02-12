package com.camunda.consulting;

import org.springframework.web.client.RestTemplate;

public class BearService {
  private static final String urlPattern = "https://placebear.com/{width}/{height}";
  public byte[] getBear(int height, int width) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(urlPattern, byte[].class, width, height);
  }
}
