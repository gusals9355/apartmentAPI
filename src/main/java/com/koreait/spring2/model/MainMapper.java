package com.koreait.spring2.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<LocationCodeEntity> selLocationCodeList();

}
