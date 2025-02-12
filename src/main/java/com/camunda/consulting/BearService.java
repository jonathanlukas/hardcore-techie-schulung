package com.camunda.consulting;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Optional.*;

@Service
public class BearService {
  private static final String urlPattern = "https://placebear.com/{width}/{height}";
  public byte[] getBear(Integer height, Integer width) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(urlPattern, byte[].class, ofNullable(width).orElse(200), ofNullable(height).orElse(200));
  }
}
