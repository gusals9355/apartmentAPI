package com.koreait.spring2.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<LocationCodeEntity> selLocationCodeList(SearchDTO searchDTO);
    List<ApartmentEntity> selApartmentInfoList(SearchDTO searchDTO);
    int insApartment(InsertEntity param);
    List<ApartmentDTO> selApartmentList(SearchDTO param);
}
