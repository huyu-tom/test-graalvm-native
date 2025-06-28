package com.huyu.testgraalvmlogmaven.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<TestA> {

  @Override
  public TestA getObject() throws Exception {
    return new TestA();
  }

  @Override
  public Class<?> getObjectType() {
    return TestA.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
