package com.huyu.testgraalvmlogmaven.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {

  @Select("select count(*) from rules")
  Long select();
}
