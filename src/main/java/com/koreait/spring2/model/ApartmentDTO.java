package com.koreait.spring2.model;

import lombok.Data;

@Data
public class ApartmentDTO {
    private int i_ai;
    private String deal_amount;
    private String build_year;
    private String deal_year;
    private String deal_month;
    private String deal_day;
    private String dong;
    private String apartment_name;
    private double area_for_exclusive_use;
    private String jibun;
    private int flr;
    private int location_cd;
    private String local_nm;
}
