package com.huyu.testgraalvmlogmaven;

import java.io.IOException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(proxyBeanMethods = false)
@MapperScan(value = {
    "com.huyu.testgraalvmlogmaven.mapper"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class TestGraalvmLogMavenApplication {

  public static void main(String[] args) throws IOException, InterruptedException {
//    DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
//    Resource resource = defaultResourceLoader.getResource("/log4j2.xml");
//    System.out.println(resource.isFile());
//    System.out.println(resource.getURL());
//    System.out.println(new String(resource.getInputStream().readAllBytes()));
    SpringApplication.run(TestGraalvmLogMavenApplication.class, args);
  }

}
