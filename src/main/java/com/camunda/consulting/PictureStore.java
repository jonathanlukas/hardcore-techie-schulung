package com.camunda.consulting;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
public class PictureStore {
  private final Map<String, byte[]> inMemoryStore = new HashMap<>();
  public void storePicture(String id, byte[] picture) {
    inMemoryStore.put(id, picture);
  }

  public byte[] getPicture(String id) {
    return inMemoryStore.get(id);
  }
}
