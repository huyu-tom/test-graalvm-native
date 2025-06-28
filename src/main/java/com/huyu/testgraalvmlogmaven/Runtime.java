package com.huyu.testgraalvmlogmaven;

import apache.rocketmq.v2.Address;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.benmanes.caffeine.cache.Cache;
import com.huyu.testgraalvmlogmaven.controller.TestController;
import com.huyu.testgraalvmlogmaven.factory.TestA;
import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.apache.calcite.adapter.enumerable.RexImpTable;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.rocketmq.acl.AccessResource;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.common.TopicConfig;
import org.apache.rocketmq.remoting.protocol.BrokerSyncInfo;
import org.apache.rocketmq.srvutil.AclFileWatchService;
import org.apache.shardingsphere.authority.yaml.config.YamlAuthorityRuleConfiguration;
import org.apache.shardingsphere.authority.yaml.config.YamlUserConfiguration;
import org.apache.shardingsphere.globalclock.core.yaml.config.YamlGlobalClockRuleConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.YamlRootConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.mode.YamlModeConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.mode.YamlPersistRepositoryConfiguration;
import org.apache.shardingsphere.logging.yaml.config.YamlAppenderConfiguration;
import org.apache.shardingsphere.logging.yaml.config.YamlLoggerConfiguration;
import org.apache.shardingsphere.logging.yaml.config.YamlLoggingRuleConfiguration;
import org.apache.shardingsphere.parser.yaml.config.YamlSQLParserCacheOptionRuleConfiguration;
import org.apache.shardingsphere.parser.yaml.config.YamlSQLParserRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.yaml.config.YamlReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.yaml.config.rule.YamlReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.sharding.algorithm.sharding.classbased.ClassBasedShardingAlgorithmFactory;
import org.apache.shardingsphere.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.cache.YamlShardingCacheConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.cache.YamlShardingCacheOptionsConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.rule.YamlTableRuleConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.audit.YamlShardingAuditStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.keygen.YamlKeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlHintShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlNoneShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlStandardShardingStrategyConfiguration;
import org.apache.shardingsphere.sqlfederation.yaml.config.YamlSQLFederationExecutionPlanCacheRuleConfiguration;
import org.apache.shardingsphere.sqlfederation.yaml.config.YamlSQLFederationRuleConfiguration;
import org.apache.shardingsphere.sqltranslator.api.config.SQLTranslatorRuleConfiguration;
import org.apache.shardingsphere.sqltranslator.yaml.config.YamlSQLTranslatorRuleConfiguration;
import org.apache.shardingsphere.traffic.yaml.config.YamlTrafficRuleConfiguration;
import org.apache.shardingsphere.traffic.yaml.config.YamlTrafficStrategyConfiguration;
import org.apache.shardingsphere.transaction.yaml.config.YamlTransactionRuleConfiguration;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.redisson.codec.MsgPackJacksonCodec;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.io.ClassPathResource;
import sun.misc.Unsafe;

@Configuration(proxyBeanMethods = false)
@ImportRuntimeHints(Runtime.class)
public class Runtime implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    //mybatis注册代理和反射
    hints.proxies().registerJdkProxy(DataSource.class).registerJdkProxy(Executor.class)
        .registerJdkProxy(ResultSetHandler.class).registerJdkProxy(ParameterHandler.class)
        .registerJdkProxy(StatementHandler.class);
    hints.reflection().registerType(MapperFactoryBean.class).registerType(Log4j2Impl.class);
    registerReflection(hints, Log.class);

    hints.reflection().registerType(TestController.class).registerType(Unsafe.class);

    //注册log4j2
    hints.resources().registerPattern("log4j2.xml");
    hints.resources().registerResource(
        new ClassPathResource("org/springframework/boot/logging/log4j2/log4j2.xml"));

    //redisson
    registerReflection(hints, RoundRobinLoadBalancer.class);
    registerReflection(hints, MsgPackJacksonCodec.class);

    //底层函数式
    registerReflection(hints, Function.class);

    //rocketmq
    registerReflection(hints, ClientConfig.class);
    registerReflection(hints, TopicConfig.class);
    registerReflection(hints, Address.class);
    registerReflection(hints, AccessResource.class);
    registerReflection(hints, org.apache.rocketmq.remoting.Configuration.class);
    registerReflection(hints, AclFileWatchService.class);
    hints.resources().registerPattern("proto/apache/rocketmq/v2/**/");
    hints.resources().registerPattern("apache/rocketmq/v2/**/*");
//    //需要被fastjson序列化和反序列化的反射类
    registerReflection(hints, BrokerSyncInfo.class, (classz) -> {
      String simpleName = classz.getSimpleName();
      String classzName = classz.getName() + "$" + simpleName + "_FASTJSONReader";
      String classzName1 = classz.getName() + "$" + simpleName + "_FASTJSONWriter";
      return Arrays.asList(classzName, classzName1);
    });

    //jackson
//    hints.reflection().registerType(JsonSerializer.class);
//    hints.reflection().registerType(JavaTimeModule.class);
//    hints.reflection().registerType(OptionalSerializer.class);
//    hints.reflection().registerType(ObjectMapper.class);
//
//    //fastjson
//    registerReflection(hints, com.alibaba.fastjson.JSON.class, null);
//    registerReflection(hints, com.alibaba.fastjson2.JSON.class, null);

    //注册sharding
    Stream.of(YamlModeConfiguration.class, YamlAuthorityRuleConfiguration.class,
        YamlPersistRepositoryConfiguration.class, YamlAuthorityRuleConfiguration.class,
        YamlUserConfiguration.class, YamlTransactionRuleConfiguration.class,
        YamlSQLParserRuleConfiguration.class, YamlSQLParserCacheOptionRuleConfiguration.class,
        YamlSQLTranslatorRuleConfiguration.class, SQLTranslatorRuleConfiguration.class,
        YamlTrafficRuleConfiguration.class, YamlTrafficStrategyConfiguration.class,
        YamlLoggingRuleConfiguration.class, YamlLoggerConfiguration.class,
        YamlAppenderConfiguration.class, Properties.class, YamlGlobalClockRuleConfiguration.class,
        YamlSQLFederationExecutionPlanCacheRuleConfiguration.class,
        YamlSQLFederationRuleConfiguration.class, YamlRootConfiguration.class,
        YamlReadwriteSplittingRuleConfiguration.class,
        YamlReadwriteSplittingDataSourceRuleConfiguration.class,

        YamlShardingRuleConfiguration.class, YamlTableRuleConfiguration.class,
        YamlShardingStrategyConfiguration.class, YamlStandardShardingStrategyConfiguration.class,
        YamlComplexShardingStrategyConfiguration.class, YamlHintShardingStrategyConfiguration.class,
        YamlNoneShardingStrategyConfiguration.class, YamlKeyGenerateStrategyConfiguration.class,
        YamlShardingAuditStrategyConfiguration.class, YamlShardingCacheConfiguration.class,
        YamlShardingCacheOptionsConfiguration.class, ClassBasedShardingAlgorithmFactory.class,
        RexImpTable.class, Arrays.class, Function.class).forEach((classz) -> {
      hints.reflection().registerType(classz, MemberCategory.values());
    });

    //注册资源  "log4j2-spring.xml",
    Stream.of("org/apache/ibatis/builder/xml/.*.dtd", "org/apache/ibatis/builder/xml/.*.xsd",
        "META-INF/**/*", "services/*.*", "*.*", "redisson.yaml", "sharding-jdbc.yaml",
        "redisson.yaml", "sharding-jdbc.yaml", "log4j2.system.properties", "es.crt", "ip/**/*",
        "lua/**/*", "sensitive/**/*").forEach(hints.resources()::registerPattern);

    //注册 druid
    hints.reflection().registerType(DruidDataSource.class, MemberCategory.values());
    hints.reflection().registerType(HikariDataSource.class, MemberCategory.values());

    //caffeine
    registerReflection(hints, Cache.class);

    //自己项目的pojo
    registerReflection(hints, TestA.class);
  }

  private void registerReflection(RuntimeHints hints, Class<?> classz) {
    registerReflection(hints, classz, null);
  }

  private void registerReflection(RuntimeHints hints, Class classz,
      Function<Class<?>, List<String>> apply) {
    try {
      var classNames = getClassesInSamePackage(classz);
      // 判断每个类是否是某个子类
      for (String className : classNames) {
        try {
          Class<?> clazz = Class.forName(className);
          // 只注册通过 shouldRegisterClass 的类及其所有内部类
          registerClassAndInnerClasses(hints, clazz, apply);
        } catch (Error e) {
          //直接忽略
        } catch (Exception e1) {
          // 忽略异常
        }
      }
    } catch (Exception exception) {
      // 忽略异常
    }
  }

  // 递归注册本类及其所有内部类，支持动态后缀
  private void registerClassAndInnerClasses(RuntimeHints hints, Class<?> clazz,
      Function<Class<?>, List<String>> apply) {
    hints.reflection().registerType(clazz, MemberCategory.values());
    for (Class<?> inner : clazz.getDeclaredClasses()) {
      registerClassAndInnerClasses(hints, inner, apply);
    }
    // 动态注册后缀
    if (apply != null) {
      List<String> classzs = apply.apply(clazz);
      if (Objects.nonNull(classzs) && !classzs.isEmpty()) {
        for (String classz : classzs) {
          System.out.println("自定义后缀生成反射类的类名是:" + classz);
          hints.reflection().registerType(org.springframework.aot.hint.TypeReference.of(classz),
              MemberCategory.values());
        }
      }
    }
  }

  // 获取指定包名下的所有类名（包括子包）
  public static List<String> getClassesInSamePackage(Class<?> clazz) {
    List<String> classNames = new ArrayList<>();
    Package pkg = clazz.getPackage();
    if (pkg != null) {
      String packageName = pkg.getName();
      try {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null) {
          classLoader = Thread.currentThread().getContextClassLoader();
        }
        Enumeration<URL> resources = classLoader.getResources(packageName.replace('.', '/'));
        while (resources.hasMoreElements()) {
          java.net.URL resource = resources.nextElement();
          if (resource.getProtocol().equals("jar")) {
            String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
              JarEntry entry = entries.nextElement();
              String entryName = entry.getName();
              // 修改这里，使用startsWith来包含所有子包
              if (entryName.startsWith(packageName.replace('.', '/')) && entryName.endsWith(
                  ".class")) {
                String className = entryName.substring(0, entryName.length() - 6).replace('/', '.');
                classNames.add(className);
              }
            }
            jarFile.close();
          } else if (resource.getProtocol().equals("file")) {
            File file = new File(resource.getPath());
            if (file.exists()) {
              addClassNameByDirectoryRecursive(classNames, packageName, file);
            }
          }
        }
      } catch (java.io.IOException e) {
        e.printStackTrace();
      }
    }
    return classNames;
  }

  // 递归添加目录下的所有类名（包括子目录）
  public static void addClassNameByDirectoryRecursive(List<String> classNames, String packageName,
      File file) {
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (files != null) {
        for (File iterFile : files) {
          if (iterFile.isDirectory()) {
            // 递归处理子目录
            String subPackageName = packageName + '.' + iterFile.getName();
            addClassNameByDirectoryRecursive(classNames, subPackageName, iterFile);
          } else if (iterFile.isFile() && iterFile.getName().endsWith(".class")) {
            // 添加当前目录下的类文件
            String className = packageName + '.' + iterFile.getName()
                .substring(0, iterFile.getName().length() - 6);
            classNames.add(className);
          }
        }
      }
    } else if (file.isFile() && file.getName().endsWith(".class")) {
      // 如果是类文件，直接添加
      String className =
          packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
      classNames.add(className);
    }
  }

  // 保留原来的方法作为兼容
  public static void addClassNameByDirectory(List<String> classNames, String startClassName,
      File file) {
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      var curStartClassName = startClassName + '.' + file.getName();
      for (File iterFile : files) {
        addClassNameByDirectory(classNames, curStartClassName, iterFile);
      }
    } else {
      classNames.add(
          startClassName + '.' + file.getName().substring(0, file.getName().length() - 6));
    }
  }

  // 判断一个类是否是另一个类的子类
  public static Class<?> getClass(String className, Class<?> superClass)
      throws ClassNotFoundException {
    Class<?> clazz = Class.forName(className);
    if (superClass.isAssignableFrom(clazz) && !clazz.equals(superClass) && !Modifier.isAbstract(
        clazz.getModifiers())) {
      return clazz;
    }
    return null;
  }

  public static void main(String[] args) {
//    try {
//      System.out.println("=== 测试 BusinessException 包下的所有类（包括子包）===");
//      var classNames = getClassesInSamePackage(BusinessException.class);
//      classNames.forEach(System.out::println);
//      System.out.println("总共找到 " + classNames.size() + " 个类");
//
//      System.out.println("\n=== 测试 Function 包下的所有类（包括子包）===");
//      List<String> classes = getClassesInSamePackage(Function.class);
//      classes.forEach(System.out::println);
//      System.out.println("总共找到 " + classes.size() + " 个类");
//
//      System.out.println("\n=== 测试 Cache 包下的所有类（包括子包）===");
//      List<String> cacheClasses = getClassesInSamePackage(Cache.class);
//      cacheClasses.forEach(System.out::println);
//      System.out.println("总共找到 " + cacheClasses.size() + " 个类");
//
//      System.out.println("\n=== 测试 Redisson 包下的所有类（包括子包）===");
//      List<String> redissonClasses = getClassesInSamePackage(Redisson.class);
//      System.out.println("Redisson 包下总共找到 " + redissonClasses.size() + " 个类");
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
}
