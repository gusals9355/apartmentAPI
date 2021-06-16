package com.koreait.spring2.model;

import lombok.Data;

@Data
public class InsertEntity {
    private int location_cd;
    private ApartmentEntity[] arr;
}
