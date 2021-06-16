package com.koreait.spring2.model;

import lombok.Data;

@Data
public class SearchDTO {
    private int deal_year;
    private int deal_month;
    private String ex_cd;
    private int location_cd;
}
