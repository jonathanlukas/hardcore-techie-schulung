package com.camunda.consulting;

import com.camunda.consulting.JobWorkers.AnimalPicture;
import io.camunda.zeebe.client.api.JsonMapper;
import io.camunda.zeebe.spring.client.bean.ParameterInfo;
import io.camunda.zeebe.spring.client.jobhandling.parameter.DefaultParameterResolverStrategy;
import io.camunda.zeebe.spring.client.jobhandling.parameter.ParameterResolver;

public class MyParameterResolverStrategy extends DefaultParameterResolverStrategy {
  private final PictureStore pictureStore;

  public MyParameterResolverStrategy(JsonMapper jsonMapper, PictureStore pictureStore) {
    super(jsonMapper);
    this.pictureStore = pictureStore;
  }

  @Override
  public ParameterResolver createResolver(ParameterInfo parameterInfo) {
    final Class<?> parameterType = parameterInfo.getParameterInfo().getType();
    if(AnimalPicture.class.isAssignableFrom(parameterType)) {
      return new AnimalPictureParameterResolver(pictureStore);
    }
    return super.createResolver(parameterInfo);
  }
}
