package com.huyu.testgraalvmlogmaven.factory;

//@com.alibaba.fastjson2.annotation.JSONCompiled
public class TestA {


  private String username;
  private String password;
  private boolean age;

  public String getUsername() {
    return username;
  }

  public TestA setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public TestA setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean isAge() {
    return age;
  }

  public TestA setAge(boolean age) {
    this.age = age;
    return this;
  }
}
